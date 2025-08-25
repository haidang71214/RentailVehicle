package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rentailmotorcar.dto.request.RoleRequest;
import com.example.rentailmotorcar.dto.response.RoleResponse;
import com.example.rentailmotorcar.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleResponse toRoleResponse(Role role);
}
