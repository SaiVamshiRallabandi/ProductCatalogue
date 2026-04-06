package com.ecom.productcatalog.dto;

import com.ecom.productcatalog.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class GetPrdctsBtCtgryRspDto extends BaseResponseDto{
    private Page<Product> productsList;

}
