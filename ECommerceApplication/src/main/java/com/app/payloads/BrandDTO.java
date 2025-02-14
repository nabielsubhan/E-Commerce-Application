package com.app.payloads;

import java.util.List;

import com.app.entites.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class BrandDTO {
	private Long brandId;
	private String brandName;
}
