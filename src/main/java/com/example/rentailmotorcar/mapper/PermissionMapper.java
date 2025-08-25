package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;

import com.example.rentailmotorcar.dto.request.PermissionRequest;
import com.example.rentailmotorcar.dto.response.PermissionResponse;
import com.example.rentailmotorcar.entity.Permission;
@Mapper(componentModel = "spring")
public interface PermissionMapper {
   Permission  toPermission(PermissionRequest permissionRequest);
   PermissionResponse toPermissionResponse(Permission permission);
}
