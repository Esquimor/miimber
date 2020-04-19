package com.miimber.back.organization.dto.organization;

import java.util.ArrayList;
import java.util.List;

import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.Organization;
import com.miimber.back.organization.model.enums.RoleEnum;
import com.stripe.model.Subscription;

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
			this.setCurrentPeriodEnd(subscription.getCurrentPeriodEnd());
			this.setCancelAtPeriodEnd(subscription.getCancelAtPeriodEnd());
			this.setStatus(subscription.getStatus());
			this.setPlan(subscription.getPlan().getId());
		}
	}
	
	public OrganizationForManageReadResponseDTO(long id, String name, List<Member> members, Subscription subscription) {
		super(id, name);
		this.setMembers(convertMembersDTO(members));
		if (subscription != null) {
			this.setCurrentPeriodEnd(subscription.getCurrentPeriodEnd());
			this.setCancelAtPeriodEnd(subscription.getCancelAtPeriodEnd());
			this.setStatus(subscription.getStatus());
			this.setPlan(subscription.getPlan().getId());
		}
	}
	
	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

	public Long getCurrentPeriodEnd() {
		return current_period_end;
	}

	public void setCurrentPeriodEnd(Long current_period_end) {
		this.current_period_end = current_period_end;
	}

	public boolean isCancelAtPeriodEnd() {
		return cancel_at_period_end;
	}

	public void setCancelAtPeriodEnd(boolean cancel_at_period_end) {
		this.cancel_at_period_end = cancel_at_period_end;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
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
				member.getType()
				);
	}

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
		
		public Long getId() {
			return this.id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getLastName() {
			return this.lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getFirstName() {
			return this.firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public RoleEnum getRole() {
			return this.role;
		}
		
		public void setRole(RoleEnum role) {
			this.role = role;
		}
	}
}
