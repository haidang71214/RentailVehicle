package com.example.rentailmotorcar.entity;

import com.example.rentailmotorcar.enums.ToOwnerRequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToOwnerRequest {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id;
   // mỗi cái này tương ứng với 1 user thôi
   @OneToOne
   User user;
   @Enumerated(EnumType.STRING)
   //  PENDING','APPROVED','REJECTED
   ToOwnerRequestStatus status;
   String cccdSideFace; // không lưu, set null
   String cccdBackFace; // set để check với ai xong xóa luôn
   // chỗ này ai sẽ mã hóa để con trừa 4 số cuối để mình đối chiếu
   String cccdNumber;
   
   boolean checkCccd;// ai làm xong sẽ trả về true hoặc false
}
