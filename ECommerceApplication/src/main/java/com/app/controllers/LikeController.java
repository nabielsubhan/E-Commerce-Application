package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.payloads.LikeDTO;
import com.app.services.LikeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class LikeController {

	@Autowired
	private LikeService likeService;

	@PostMapping("/public/likes")
	public ResponseEntity<LikeDTO> addLike(@Valid @RequestBody LikeDTO likeDTO) {

        LikeDTO savedLikeDTO = likeService.createLike(likeDTO);
		return new ResponseEntity<LikeDTO>(savedLikeDTO, HttpStatus.CREATED);
	}

	@GetMapping("/public/users/{email}/likes")
	public ResponseEntity<List<LikeDTO>> getLikesByUser(@PathVariable String email) {
		List<LikeDTO> likes = likeService.getLikesByUser(email);
		
		return new ResponseEntity<List<LikeDTO>>(likes, HttpStatus.FOUND);
	}

    @DeleteMapping("/public/users/{email}/likes/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable String email, @PathVariable Long likeId) {
        likeService.deleteLike(email, likeId);
        return ResponseEntity.ok("Like with ID: " + likeId + " deleted successfully for user: " + email + "!");
    }
    
    @GetMapping("/admin/likes")
    public ResponseEntity<List<LikeDTO>> getAllLikes() {
        List<LikeDTO> likes = likeService.getAllLikes();
        return ResponseEntity.ok(likes);
    }
}
