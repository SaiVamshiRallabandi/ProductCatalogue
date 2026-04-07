package com.ecom.productcatalog.repository;

import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.model.RecordState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCatalogRepository extends JpaRepository<Product,Long> {

    List<Product> getProductsByCategory_TitleAndState(String categoryTitle, RecordState state);

    <S extends Product> S save(S entity);

    Page<Product> getProductsByCategory_TitleAndState(String categoryTitle, RecordState state, Pageable pageable);

}
