package com.app.services;

import com.app.entites.Brand;
import com.app.entites.Like;
import com.app.payloads.LikeDTO;
import com.app.payloads.OrderDTO;
import com.app.payloads.ProductDTO;

import java.util.List;

public interface LikeService {
    LikeDTO createLike(LikeDTO likeDTO);
    List<LikeDTO> getLikesByUser(String email);
    void deleteLike(String email, Long likeId);
    List<LikeDTO> getAllLikes();
}
