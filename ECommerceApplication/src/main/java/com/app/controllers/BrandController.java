package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.Brand;
import com.app.payloads.BrandDTO;
import com.app.services.BrandService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping("/admin/brands")
	public ResponseEntity<BrandDTO> addBrand(@Valid @RequestBody Brand brand) {

        BrandDTO savedBrandDTO = brandService.createBrand(brand);
		return new ResponseEntity<BrandDTO>(savedBrandDTO, HttpStatus.CREATED);
	}
}
