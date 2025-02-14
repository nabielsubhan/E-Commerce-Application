package com.app.services;

import com.app.entites.Brand;
import com.app.entites.Category;
import com.app.payloads.BrandDTO;

public interface BrandService {
	public BrandDTO createBrand(Brand brand);
}
