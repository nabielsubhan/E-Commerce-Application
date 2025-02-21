package com.app.payloads;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CouponDTO {
    private Long couponId;
    private String couponCode;
    private double discountPercentage;
    private int quota;
    private LocalDate startDate;
    private LocalDate endDate;
}
