package com.app.controllers;

import java.io.IOException;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.config.AppConstants;
import com.app.entites.Brand;
import com.app.entites.Product;
import com.app.payloads.BrandDTO;
import com.app.payloads.ProductDTO;
import com.app.payloads.ProductResponse;
import com.app.services.BrandService;
import com.app.services.ProductService;



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