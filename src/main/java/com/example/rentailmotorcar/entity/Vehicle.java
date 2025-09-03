package com.example.rentailmotorcar.entity;

import java.util.Set;
import com.example.rentailmotorcar.enums.BrandEnums;
import com.example.rentailmotorcar.enums.VehicleStatus;
import com.example.rentailmotorcar.enums.VehicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id;
   Set<String> imageUrl;
   @Enumerated(EnumType.STRING)
   VehicleType types;
   String name;
   @Enumerated(EnumType.STRING)
   VehicleStatus status; // đang thuê, đang rảnh / đang bảo dưỡng
   int star;   // star 
   int price; // giá
   @Enumerated(EnumType.STRING)
   BrandEnums brand;
   @ManyToOne  // 1 người có thể cho thuê nhiều xe
   User owner;

   // tạo bảng riêng
   // khi tới hết hạn thì xóa cái rentailer đi 
   @OneToOne // 1 phương tiện chỉ có 1 người thuê
   User rentailer;
   @OneToMany
   Set<Review> reviews;
}
