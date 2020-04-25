package com.miimber.back.organization.dto.organization;

import java.util.ArrayList;
import java.util.List;

import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.Organization;
import com.miimber.back.organization.model.enums.RoleEnum;
import com.stripe.model.Subscription;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganizationForManageReadResponseDTO extends OrganizationCreateReadUpdateResponseDTO {

	private List<MemberDTO> members;
	private Long current_period_end;
	private boolean cancel_at_period_end;
	private String status;
	private String plan;
	
	public OrganizationForManageReadResponseDTO(long id, String name) {
		super(id, name);
	}
	
	public OrganizationForManageReadResponseDTO(Organization organization, Subscription subscription) {
		super(organization.getId(), organization.getName());
		this.setMembers(convertMembersDTO(organization.getMembers()));
		if (subscription != null) {
			this.setCurrent_period_end(subscription.getCurrentPeriodEnd());
			this.cancel_at_period_end = subscription.getCancelAtPeriodEnd();
			this.setStatus(subscription.getStatus());
			this.setPlan(subscription.getPlan().getId());
		}
	}
	
	public OrganizationForManageReadResponseDTO(long id, String name, List<Member> members, Subscription subscription) {
		super(id, name);
		this.setMembers(convertMembersDTO(members));
		if (subscription != null) {
			this.setCurrent_period_end(subscription.getCurrentPeriodEnd());
			this.cancel_at_period_end = subscription.getCancelAtPeriodEnd();
			this.setStatus(subscription.getStatus());
			this.setPlan(subscription.getPlan().getId());
		}
	}
	
	private List<MemberDTO> convertMembersDTO(List<Member> members) {
		List<MemberDTO> membersConverted = new ArrayList<MemberDTO>();
		for (Member member : members) 
        { 
			membersConverted.add(convertMemberDTO(member));
        }
		return membersConverted;
	}
	
	private MemberDTO convertMemberDTO(Member member) {
		return new MemberDTO(
				member.getId(),
				member.getUser().getLastName(),
				member.getUser().getFirstName(),
				member.getRole()
				);
	}

	@Getter @Setter
	private class MemberDTO {
		
		private Long id;
		private String lastName;
		private String firstName;
		private RoleEnum role;
		
		public MemberDTO(Long id, String lastName, String firstName, RoleEnum role) {
			this.id = id;
			this.lastName = lastName;
			this.firstName = firstName;
			this.role = role;
		}
	}
}
