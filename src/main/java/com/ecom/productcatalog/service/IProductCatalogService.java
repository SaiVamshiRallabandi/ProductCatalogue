package com.ecom.productcatalog.service;

import com.ecom.productcatalog.ExceptionHandler.NoProductsFoundException;
import com.ecom.productcatalog.dto.SortingCriteria;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.model.RecordState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductCatalogService {

    public List<Product> fetchProductsByCategory(String category, RecordState state);
    public List<Product> fetchProductsByName(String productName, RecordState state);
    public Product saveProduct(Product product);

    public Page<Product> fetchProductsByCategory(String category, RecordState state, int pageNumber, int pageSize, SortingCriteria sortCriteria);

    public Product fetchProductById(Long prodId) throws NoProductsFoundException;
}
