package com.app.services;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.APIException;
import com.app.repositories.CouponRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CouponServiceImpl implements CouponService {

        @Autowired
        private CouponRepo couponRepo;

        @Autowired
        private ModelMapper modelMapper;

        @Override
        public CouponDTO createCoupon(Coupon coupon) {
            Coupon savedCoupon = couponRepo.findByCouponCode(coupon.getCouponCode()).orElseThrow(() -> new APIException("Coupon with the code '" + coupon.getCouponCode() + "Does not exist !!!"));

            if (savedCoupon != null) {
                throw new APIException("Coupon with the code '" + coupon.getCouponCode() + "' already exists !!!");
            }

            savedCoupon = couponRepo.save(coupon);

            return modelMapper.map(savedCoupon, CouponDTO.class);
        }

}
