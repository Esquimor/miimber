package com.miimber.back.organization.dto.member;

import com.miimber.back.organization.model.enums.RoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberUpdateRequestDTO {

	private RoleEnum role = RoleEnum.MEMBER;
}
