package com.ecom.productcatalog.dto;

import com.ecom.productcatalog.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductResponseDto extends BaseResponseDto{
    Product product;
}
