package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;

import com.example.rentailmotorcar.dto.request.ReviewRequest;
import com.example.rentailmotorcar.dto.response.ReviewResponse;
import com.example.rentailmotorcar.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
   Review toReview(ReviewRequest reviewRequest);
   ReviewResponse toReviewResponse(Review review);
}
