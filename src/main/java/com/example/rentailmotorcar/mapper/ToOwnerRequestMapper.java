package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rentailmotorcar.dto.request.ToOwnerReqDto;
import com.example.rentailmotorcar.dto.response.ToOwnerResDto;
import com.example.rentailmotorcar.entity.ToOwnerRequest;

@Mapper(componentModel = "spring")
public interface ToOwnerRequestMapper {
    @Mapping(target = "cccdSideFace",ignore = true)
    @Mapping(target = "cccdBackFace",ignore = true)
    @Mapping(target = "cccdNumber",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "status",ignore = true)
    ToOwnerRequest toToOwnerRequest(ToOwnerReqDto toOwnerReqDto);
    ToOwnerResDto toToOwnerResponse(ToOwnerRequest toOwnerRequest);
}
