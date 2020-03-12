package com.tockys.back.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.tockys.back.dto.OrganizationCreateDTO;
import com.tockys.back.dto.OrganizationDTO;
import com.tockys.back.dto.OrganizationManageDTO;
import com.tockys.back.dto.OrganizationRequestDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.helper.StripeService;
import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RoleEnum;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.OrganizationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private StripeService stripeService;
	
	@Autowired
	private Helper helper;

	@RequestMapping(value = "/organization/ownered", method = RequestMethod.GET)
	public ResponseEntity<?> getOrganization() throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = helper.getUserToken(currentUser);
        List<OrganizationDTO> responseOrganization = new ArrayList<OrganizationDTO>();
        for (Organization organization : organizationService.getOrganizationOwnered(user)) 
        { 
        	responseOrganization.add(OrganizationToDTO(organization));
        }
		return ResponseEntity.ok(responseOrganization);
	}
	
	@RequestMapping(value = "/organization/{id}/manage", method = RequestMethod.GET)
	public ResponseEntity<?> getOrganizationById(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member member = memberService.getMemberByOrganizationIdAndByUser(id, user);
        
        if (member == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        if (member.getType().equals(RoleEnum.MEMBER)) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        return ResponseEntity.ok(OrganizationToManageDTO(
        		member.getOrganization(), 
				stripeService.getSubscription(member.getOrganization().getStripe())
			));
	}
	
	@RequestMapping(value = "/organization/", method = RequestMethod.POST)
	public ResponseEntity<?> createOrganization(@RequestBody OrganizationCreateDTO organizationDto) throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = helper.getUserToken(currentUser);
        
        Customer customer = stripeService.createCustomer(organizationDto.getTokenId(), user.getEmail());
        
        Subscription subscription = stripeService.createSubscription(customer, organizationDto.getSubscription());
        
        Organization newOrganization = new Organization(organizationDto.getName());
        newOrganization= organizationService.createOrganization(newOrganization);
        newOrganization.setStripe(subscription.getId());
        
        Member newMember = new Member();
        newMember.setOrganization(newOrganization);
        newMember.setUser(user);
        newMember.setType(RoleEnum.OWNER);
        newMember = memberService.createMember(newMember);
        
        newOrganization.addMember(newMember);
        
        return ResponseEntity.ok(OrganizationToDTO(newOrganization));
	}

	@RequestMapping(value = "/organization/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> nameExit(@PathVariable String name) throws Exception {
		Organization organization = organizationService.getOrganizationByName(name);
		if (organization == null) {
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/organization/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editOrganization(@RequestBody OrganizationRequestDTO organizationDto, @PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member memberUser = memberService.getMemberByOrganizationIdAndByUser(id, user);
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Organization organization = organizationService.getOrganization(id);
        organization.setName(organizationDto.getName());
        return ResponseEntity.ok(OrganizationToDTO(organizationService.editOrganization(organization)));
	}
	
	private OrganizationDTO OrganizationToDTO(Organization organization) {
		return new OrganizationDTO(
        				organization.getId(),
        				organization.getName()
        		);
	}
	
	private OrganizationManageDTO OrganizationToManageDTO(Organization organization, Subscription subscription) {
		return new OrganizationManageDTO(
				organization.getId(),
				organization.getName(),
				organization.getMembers(),
				 subscription
			);
	}
}
