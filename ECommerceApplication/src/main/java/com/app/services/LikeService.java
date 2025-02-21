package com.app.services;
import com.app.payloads.LikeDTO;
import java.util.List;

public interface LikeService {
    LikeDTO createLike(LikeDTO likeDTO);
    List<LikeDTO> getLikesByUser(String email);
    void deleteLike(String email, Long likeId);
    List<LikeDTO> getAllLikes();
}
