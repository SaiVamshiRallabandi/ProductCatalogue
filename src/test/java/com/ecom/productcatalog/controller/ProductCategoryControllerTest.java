package com.ecom.productcatalog.controller;

import com.ecom.productcatalog.dto.GetPrdctsBtCtgryRspDto;
import com.ecom.productcatalog.dto.ResponseStatus;
import com.ecom.productcatalog.dto.SortingCriteria;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.model.RecordState;
import com.ecom.productcatalog.service.IProductCatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductCategoryControllerTest {
    @Autowired
    ProductCategoryController controller;
    @MockitoBean
    IProductCatalogService service;

    @Test
    public  void getProductsByCategoryTestWhenRecordsAvailable(){
        Product product=new Product();
        product.setTitle("Mac Book");
        product.setPrice(99999.00);

        List<Product> productsList=List.of(product);
        Page<Product> products=new PageImpl<>(productsList);

    when(service.fetchProductsByCategory("Electronics", RecordState.ACTIVE,0,10,SortingCriteria.PRICE_HIGH_LOW)).thenReturn(products);
        ResponseEntity<GetPrdctsBtCtgryRspDto> response=controller.getProductsByCategory("Electronics",0,10,SortingCriteria.PRICE_HIGH_LOW);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getProductsList(),products);
        Assertions.assertEquals(response.getBody().getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void getProductsByCategoryTestWhenNoRecordsAvailable(){
        Page<Product> products=new PageImpl<>(List.of());
        when(service.fetchProductsByCategory("Textiles",RecordState.ACTIVE,1,10, SortingCriteria.PRICE_HIGH_LOW)).thenReturn(products);
        ResponseEntity<GetPrdctsBtCtgryRspDto> response=controller.getProductsByCategory("Textiles",1,10,SortingCriteria.PRICE_HIGH_LOW);
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getStatus(),ResponseStatus.FAILURE);
    }

}
