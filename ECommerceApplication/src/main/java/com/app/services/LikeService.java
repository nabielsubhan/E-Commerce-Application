package com.app.services;

import com.app.payloads.LikeDTO;
import com.app.payloads.ProductDTO;

import java.util.List;

public interface LikeService {

    List<LikeDTO> getLikesByUser(String email);
    ProductDTO getLikeProduct(String email, Long productId);
    LikeDTO addLike(String email, Long productId);
}
