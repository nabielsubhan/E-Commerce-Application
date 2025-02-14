package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Brand;
import com.app.exceptions.APIException;
import com.app.payloads.BrandDTO;
import com.app.repositories.BrandRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepo brandRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BrandDTO createBrand(Brand brand) {
		Brand savedBrand = brandRepo.findByBrandName(brand.getBrandName());

		if (savedBrand != null) {
			throw new APIException("Brand with the name '" + brand.getBrandName() + "' already exists !!!");
		}

		savedBrand = brandRepo.save(brand);

		return modelMapper.map(savedBrand, BrandDTO.class);
	}
}
