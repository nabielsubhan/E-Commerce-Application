package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Coupon;

import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Long> {


    Optional<Coupon> findByCouponCode(String couponCode);
}
