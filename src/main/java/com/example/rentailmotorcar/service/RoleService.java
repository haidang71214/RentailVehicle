package com.example.rentailmotorcar.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rentailmotorcar.dto.request.RoleRequest;
import com.example.rentailmotorcar.dto.response.RoleResponse;
import com.example.rentailmotorcar.entity.Role;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.RoleMapper;
import com.example.rentailmotorcar.repository.PermissionRepository;
import com.example.rentailmotorcar.repository.RoleRepository;
import com.example.rentailmotorcar.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class RoleService {
   RoleRepository roleRepository;
   PermissionRepository permissionRepository;
   RoleMapper roleMapper;
   UserRepository userRepository;
// create
// delete
   public RoleResponse createRole(RoleRequest roleRequest){
         Role role = roleMapper.toRole(roleRequest);
         var permission=  permissionRepository.findAllById(roleRequest.getPermissions());
         role.setPermissions(new HashSet<>(permission));
         role = roleRepository.save(role);
         return roleMapper.toRoleResponse(role);
   }
   public void deleteRole(String id){
      Role role = roleRepository.findById(id).orElseThrow(()->(new AppException(ErrorCode.ROLE_NOT_FOUND)));
      for(User user:userRepository.findAll()){
        user.getRoles().remove(role);
      }
      roleRepository.delete(role);
   }
   public List<RoleResponse> getAllRole(){
      return roleRepository.findAll().stream().map(roleMapper :: toRoleResponse).toList();
   }


}
