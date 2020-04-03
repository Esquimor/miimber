package com.tockys.back.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRoleEnum, String> {

	@Override
	public String convertToDatabaseColumn(UserRoleEnum role) {
        if (role == null) {
            return null;
        }
        return role.getRole();
	}

	@Override
	public UserRoleEnum convertToEntityAttribute(String role) {
		if (role == null) {
        return null;
		}

	    return Stream.of(UserRoleEnum.values())
	      .filter(c -> c.getRole().equals(role))
	      .findFirst()
	      .orElseThrow(IllegalArgumentException::new);
	}

}
