package com.app.controllers;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/admin/coupons")
    public ResponseEntity<CouponDTO> addCoupon(@Valid @RequestBody Coupon coupon) {

        CouponDTO savedCouponDTO = couponService.createCoupon(coupon);
        return new ResponseEntity<CouponDTO>(savedCouponDTO, HttpStatus.CREATED);
    }


}