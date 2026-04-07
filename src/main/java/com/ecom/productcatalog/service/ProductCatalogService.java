package com.ecom.productcatalog.service;

import com.ecom.productcatalog.dto.SortingCriteria;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.model.RecordState;
import com.ecom.productcatalog.repository.ProductCatalogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCatalogService implements IProductCatalogService{
    ProductCatalogRepository repo;
    SortFactory sortFactory;
    ProductCatalogService (ProductCatalogRepository repo,SortFactory sortFactory){
        this.repo=repo;
        this.sortFactory=sortFactory;
    }
    @Override
    public List<Product> fetchProductsByCategory(String category, RecordState state) {
        return repo.getProductsByCategory_TitleAndState(category,state);
    }

    @Override
    public List<Product> fetchProductsByName(String productName, RecordState state) {
        return List.of();
    }

    @Override
    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Page<Product> fetchProductsByCategory(String category, RecordState state, int pageNumber, int pageSize, SortingCriteria sortCriteria) {
        Sort sort=sortFactory.getSortObject(sortCriteria);
        Pageable pageable= PageRequest.of( pageNumber, pageSize,sort);

        return repo.getProductsByCategory_TitleAndState(category,state,pageable);
    }
}
