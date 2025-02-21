package com.app.services;

import com.app.entites.Like;
import com.app.entites.Product;
import com.app.entites.User;
import com.app.exceptions.APIException;
import com.app.payloads.LikeDTO;
import com.app.repositories.LikeRepo;
import com.app.repositories.ProductRepo;
import com.app.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LikeDTO createLike(LikeDTO likeDTO) {

        String userEmail = likeDTO.getUserEmail();
        Long productId = likeDTO.getProductId();

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new APIException("User with email: " + userEmail + " not found!"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new APIException("Product with ID: " + productId + " not found!"));

        Like existingLike = likeRepo.findByUser_EmailAndProduct_ProductId(userEmail, productId);
        if (existingLike != null) {
            throw new APIException("Like already exists with ID: " + existingLike.getLikeId());
        }

        Like like = new Like();
        like.setUser(user);
        like.setProduct(product);

        Like savedLike = likeRepo.save(like);

        return modelMapper.map(savedLike, LikeDTO.class);
    }

    @Override
    public List<LikeDTO> getLikesByUser(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new APIException("Email cannot be null or empty!");
        }

        String cleanedEmail = email.trim();

        userRepo.findByEmail(cleanedEmail)
                .orElseThrow(() -> new APIException("User with email: " + cleanedEmail + " not found!"));

        List<Like> likes = likeRepo.findAllByUserEmail(cleanedEmail);

        if (likes.isEmpty()) {
            throw new APIException("No likes found for user with email: " + cleanedEmail);
        }

        return likes.stream()
                .map(like -> modelMapper.map(like, LikeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLike(String email, Long likeId) {
        if (email == null || email.trim().isEmpty()) {
            throw new APIException("Email cannot be null or empty!");
        }
    
        String cleanedEmail = email.trim();
    
        User user = userRepo.findByEmail(cleanedEmail)
                .orElseThrow(() -> new APIException("User with email: " + cleanedEmail + " not found!"));
    
        Like like = likeRepo.findByLikeId(likeId);
        if (like == null) {
            throw new APIException("Like with ID: " + likeId + " not found!");
        }
    
        if (!like.getUser().getUserId().equals(user.getUserId())) {
            throw new APIException("Like with ID: " + likeId + " does not belong to user: " + cleanedEmail);
        }
    
        likeRepo.delete(like);
    }
    
    @Override
    public List<LikeDTO> getAllLikes() {
        List<Like> allLikes = likeRepo.findAll();
    
        if (allLikes.isEmpty()) {
            throw new APIException("No likes found in the system!");
        }
    
        return allLikes.stream()
                .map(like -> modelMapper.map(like, LikeDTO.class))
                .collect(Collectors.toList());
    }
    
}