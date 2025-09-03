package com.example.rentailmotorcar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentailmotorcar.dto.request.ReviewRequest;
import com.example.rentailmotorcar.dto.response.ApiResponse;
import com.example.rentailmotorcar.dto.response.ReviewResponse;
import com.example.rentailmotorcar.service.ReviewService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/Review")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {
   ReviewService reviewService;
   @PostMapping
   public ApiResponse<ReviewResponse> createReview(@Valid @RequestBody ReviewRequest request){
      return ApiResponse.<ReviewResponse>builder()
      .code(200).results(reviewService.createReview(request)).build();
   }
   @GetMapping("{id}")
   public ApiResponse<List<ReviewResponse>> getReviewByVehicleId(@PathVariable String id){
      return ApiResponse.<List<ReviewResponse>>builder()
      .results(reviewService.getReviewByid(id))
      .code(200)
      .build();
   }
   @DeleteMapping("{id}")
   public ApiResponse<Void> deleteReviewById(@PathVariable String id){
      return ApiResponse.<Void>builder()
      .results(reviewService.deleteReviewById(id))
      .message("Delete successful")
      .code(200)
      .build();
   }
}
