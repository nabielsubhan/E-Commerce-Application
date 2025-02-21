package com.app.repositories;
import com.app.entites.Brand;
import com.app.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.OrderItem;

import java.util.List;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    Like findByLikeId(Long likeId);
    Like findByUser_EmailAndProduct_ProductId(String email, Long productId);
    List<Like> findAllByUserEmail(String email);
}
