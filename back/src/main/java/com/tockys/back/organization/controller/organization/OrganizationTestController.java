package com.tockys.back.organization.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.tockys.back.core.helper.Helper;
import com.tockys.back.core.helper.StripeService;
import com.tockys.back.organization.dto.organization.OrganizationCreateReadUpdateResponseDTO;
import com.tockys.back.organization.dto.organization.OrganizationCreateRequestDTO;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.organization.service.MemberService;
import com.tockys.back.organization.service.OrganizationService;
import com.tockys.back.session.model.TypeSession;
import com.tockys.back.session.service.TypeSessionService;
import com.tockys.back.user.model.User;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizationTestController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TypeSessionService typeSessionService;
	
	@Autowired
	private Helper helper;
	
	@RequestMapping(value = "/test/organization/", method = RequestMethod.POST)
	public ResponseEntity<?> readOrganizationAll(@RequestBody OrganizationCreateRequestDTO organizationDto) throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = helper.getUserToken(currentUser);
        
        Organization newOrganization = new Organization(organizationDto.getName());
        newOrganization= organizationService.create(newOrganization);
        newOrganization.setStripe(null);
        
        Member newMember = new Member();
        newMember.setOrganization(newOrganization);
        newMember.setUser(user);
        newMember.setType(RoleEnum.OWNER);
        newMember = memberService.create(newMember);
        
        newOrganization.addMember(newMember);
        
        TypeSession newTypeSession = new TypeSession();
        newTypeSession.setOrganization(newOrganization);
        newTypeSession.setName("Default");
        
        typeSessionService.create(newTypeSession);
        
        return ResponseEntity.ok(new OrganizationCreateReadUpdateResponseDTO(newOrganization));
	}
}
