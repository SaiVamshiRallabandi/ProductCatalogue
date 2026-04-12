package com.ecom.productcatalog.controller;

import com.ecom.productcatalog.ExceptionHandler.NoProductsFoundException;
import com.ecom.productcatalog.dto.*;
import com.ecom.productcatalog.dto.ResponseStatus;
import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.model.RecordState;
import com.ecom.productcatalog.service.IProductCatalogService;
import com.ecom.productcatalog.service.ProductCatalogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCatalog")
public class ProductCategoryController {
    IProductCatalogService service;
    ProductCategoryController(IProductCatalogService service){
        this.service=service;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<GetPrdctsBtCtgryRspDto> getProductsByCategory(@RequestParam String category,@RequestParam int pageNo, @RequestParam int pageSize,@RequestParam SortingCriteria sortCriteria) throws NoProductsFoundException {

        Page<Product> products=service.fetchProductsByCategory(category, RecordState.ACTIVE,pageNo,pageSize,sortCriteria);
        GetPrdctsBtCtgryRspDto rspDto=new GetPrdctsBtCtgryRspDto();
        if(!products.isEmpty()){
            rspDto.setStatus(ResponseStatus.SUCCESS);
            rspDto.setProductsList(products);
        }else{
        throw new NoProductsFoundException("No products found");
        }
        return ResponseEntity.ok().body(rspDto);
    }

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody AddProductRequestDto reqDto){
        Product product=new Product();
        product.setTitle(reqDto.getProductName());
        product.setDescription(reqDto.getProductDescription());
        product.setPrice(reqDto.getPrice());
        product.setState(RecordState.ACTIVE);
        product.setCategory(new Category(reqDto.getProductCategory(),null));
        Product resp=service.saveProduct(product);
        AddProductResponseDto responseDto=new AddProductResponseDto();
        if(resp!=null){
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setProduct(product);
        }else{
            responseDto.setStatus(ResponseStatus.FAILURE);
            responseDto.setExceptions(List.of(new ExceptionDto("002","Product not saved")));
        }
        return ResponseEntity.ok().body(responseDto);
    }


}
