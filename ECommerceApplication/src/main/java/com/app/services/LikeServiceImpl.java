package com.app.services;

import com.app.entites.Like;
import com.app.entites.Product;
import com.app.entites.User;
import com.app.payloads.LikeDTO;
import com.app.payloads.ProductDTO;
import com.app.repositories.LikeRepo;
import com.app.repositories.ProductRepo;
import com.app.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        public List<LikeDTO> getLikesByUser(String email) {
            List<Like> likeList = likeRepo.findAllByEmail(email);

            return likeList.stream().map(like -> {
                LikeDTO likeDTO = new LikeDTO();
                likeDTO.setLikeId(like.getLikeId());
                likeDTO.setUserName(like.getUser().getEmail());
                likeDTO.setProductId(like.getProduct().getProductId());
                return likeDTO;
            }).toList();
        }

        @Override
        public ProductDTO getLikeProduct(String email, Long productId) {
            Like like = likeRepo.findByEmailAndProductId(email, productId);
            if (like == null) {
                throw new RuntimeException("Like belum ada");
            }
            Product product = like.getProduct();
            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setImage(product.getImage());
            productDTO.setDiscount(product.getDiscount());
            productDTO.setSpecialPrice(product.getSpecialPrice());

            return productDTO;
        }

        @Override
        public LikeDTO addLike(LikeDTO like) {
            Like likeFromDB = likeRepo.findByEmailAndProductId(like.getUserName(), like.getProductId());
            if(likeFromDB != null) {
                throw new RuntimeException("Like already exists with likeId: " + likeFromDB.getLikeId());
            }

            Optional<User> user = userRepo.findByEmail(like.getUserName());

            Like likeDisimpan = modelMapper.map(like, Like.class);

            Like likeToDB = likeRepo.save(likeDisimpan);

            return modelMapper.map(likeToDB, LikeDTO.class);
        }

        public LikeDTO deleteLike(Long likeId) {
            return null;
        }
}
