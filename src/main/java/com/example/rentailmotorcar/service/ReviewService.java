package com.example.rentailmotorcar.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.rentailmotorcar.dto.request.ReviewRequest;
import com.example.rentailmotorcar.dto.response.ReviewResponse;
import com.example.rentailmotorcar.entity.HistoryRentail;
import com.example.rentailmotorcar.entity.Review;
import com.example.rentailmotorcar.entity.Vehicle;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.ReviewMapper;
import com.example.rentailmotorcar.repository.HistoryRentalRepository;
import com.example.rentailmotorcar.repository.ReviewRepository;
import com.example.rentailmotorcar.repository.VehicleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {
   ReviewRepository reviewRepository;
   HistoryRentalRepository historyRentalRepository;
   ReviewMapper reviewMapper;
   VehicleRepository vehicleRepository;
    @PreAuthorize("hasRole('CLIENT')")
   public ReviewResponse createReview(ReviewRequest reviewRequest){
      // check trong history xem user đã thuê xe chưa
      List<HistoryRentail> histories = historyRentalRepository.findAll();

        boolean hasRented = histories.stream()
                .filter(h -> h.getRenter().getId().equals(reviewRequest.getUser().getId()))
                .anyMatch(h -> h.getVehicles()
                        .stream()
                        .anyMatch(v -> v.getId().equals(reviewRequest.getVehicle().getId())));

        if (!hasRented) {
            throw new IllegalStateException();
        }
        Review review = reviewMapper.toReview(reviewRequest);
        reviewRepository.save(review);
        return reviewMapper.toReviewResponse(review);
   }
   // id của cái vehicle
   public List<ReviewResponse> getReviewByid(String id){
      Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.VEHICLE_NOT_FOUND));
      return reviewRepository.findByVehicle(vehicle).stream().map(reviewMapper :: toReviewResponse).toList();
   }
   // delete review vehicle user
   // xóa review của khách hàng ha

   public Void deleteReviewById(String id){
       Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
    reviewRepository.delete(review);
    return null;
   }
}
