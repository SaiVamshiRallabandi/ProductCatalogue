package com.ecom.productcatalog.dto;

import com.ecom.productcatalog.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchProductsResponseDto extends BaseResponseDto{
    private Page<Product> productsList;

}
