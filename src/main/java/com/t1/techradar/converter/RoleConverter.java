package com.t1.techradar.converter;

import com.t1.techradar.model.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, Long> {
    @Override
    public Long convertToDatabaseColumn(Role role) {
        return role.getRoleId();
    }

    @Override
    public Role convertToEntityAttribute(Long aLong) {
        return null;
    }
}
