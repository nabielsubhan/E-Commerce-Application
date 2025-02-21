package com.app.repositories;
import com.app.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.OrderItem;

import java.util.List;

@Repository
public interface LikeRepo extends JpaRepository<OrderItem, Long> {
    Like findByEmailAndProductId(String email, Long productId);

    List<Like> findAllByEmail(String email);

    void deleteById(Long id);
}
