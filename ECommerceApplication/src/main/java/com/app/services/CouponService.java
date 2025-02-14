package com.app.services;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;

public interface CouponService {
    CouponDTO createCoupon(Coupon coupon);

}
