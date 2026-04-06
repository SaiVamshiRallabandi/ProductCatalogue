package com.ecom.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequestDto {
    String productName;
    String productDescription;
    String productCategory;
    double price;
}
