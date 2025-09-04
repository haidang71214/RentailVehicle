package com.example.rentailmotorcar.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentailmotorcar.dto.request.ToOwnerReqDto;
import com.example.rentailmotorcar.dto.response.ToOwnerResDto;
import com.example.rentailmotorcar.entity.ToOwnerRequest;
import com.example.rentailmotorcar.enums.ToOwnerRequestStatus;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.ToOwnerRequestMapper;
import com.example.rentailmotorcar.repository.ToOwnerRequestRepository;
import com.example.rentailmotorcar.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ToOwnerRequestService {
   ToOwnerRequestMapper toOwnerRequestMapper;
   ToOwnerRequestRepository toOwnerRequestRepository;
   CloudinaryService cloudinaryService;
   UserRepository userRepository;
// gửi 2 ảnh cccd, với 1 cái requestDto xong Ai sẽ đánh giá là cái cccd có ok không
@PreAuthorize("hasRole('CLIENT')")
   public ToOwnerResDto createToChangeRole(String userId,ToOwnerReqDto toOwnerReqDto,MultipartFile image1,MultipartFile image2) throws IOException{
      ToOwnerRequest toOwnerRequest = toOwnerRequestMapper.toToOwnerRequest(toOwnerReqDto);

      toOwnerRequest.setCccdBackFace(cloudinaryService.uploadFile(image2));
      toOwnerRequest.setCccdSideFace(cloudinaryService.uploadFile(image1));
// chỗ đằng trên sẽ thay bằng ai xong gán vào cái đằng dưới
      toOwnerRequest.setCccdNumber(null);
      // set trạng thái
      toOwnerRequest.setUser(userRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)));
      toOwnerRequest.setStatus(ToOwnerRequestStatus.PENDING);
      // lưu 
      toOwnerRequestRepository.save(toOwnerRequest);
      // in ra
      return toOwnerRequestMapper.toToOwnerResponse(toOwnerRequest);
   }
   // lấy list, accept, reject, phân loại đã accept hay chưa thì để fontend lo
   @PreAuthorize("hasRole('ADMIN')")
   public List<ToOwnerResDto> getListOwnerRequest(){
      return toOwnerRequestRepository.findAll().stream().map(toOwnerRequestMapper :: toToOwnerResponse).toList();
   }
   @PreAuthorize("hasRole('ADMIN')")
   public ToOwnerResDto acceptOwnerRequest(String requestId){
      ToOwnerRequest toOwnerRequest = toOwnerRequestRepository.findById(requestId).orElseThrow(()-> new AppException(ErrorCode.OWNER_REUQEST_NOT_FOUND));
      toOwnerRequest.setStatus(ToOwnerRequestStatus.APPROVED);
      return toOwnerRequestMapper.toToOwnerResponse(toOwnerRequest);
   }
      @PreAuthorize("hasRole('ADMIN')")
   public ToOwnerResDto rejectOwnerRequest(String requestId){
      ToOwnerRequest toOwnerRequest = toOwnerRequestRepository.findById(requestId).orElseThrow(()-> new AppException(ErrorCode.OWNER_REUQEST_NOT_FOUND));
      toOwnerRequest.setStatus(ToOwnerRequestStatus.REJECTED);
      return toOwnerRequestMapper.toToOwnerResponse(toOwnerRequest);
   }
}
