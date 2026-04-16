package com.ecom.productcatalog.service;

import com.ecom.productcatalog.ExceptionHandler.NoProductsFoundException;
import com.ecom.productcatalog.dto.SortingCriteria;
import com.ecom.productcatalog.eventHandler.KafkaProducerClient;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.model.RecordState;
import com.ecom.productcatalog.repository.ProductCatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCatalogService implements IProductCatalogService{
    KafkaProducerClient producerClient;
    ProductCatalogRepository repo;
    SortFactory sortFactory;
    RedisTemplate<String,Object> redisTemplate;
    ObjectMapper objectMapper;
    ProductCatalogService (ProductCatalogRepository repo, SortFactory sortFactory,
                           RedisTemplate<String,Object> redisTemplate, ObjectMapper objectMapper, KafkaProducerClient producerClient){
        this.repo=repo;
        this.sortFactory=sortFactory;
        this.redisTemplate=redisTemplate;
        this.objectMapper=objectMapper;
        this.producerClient = producerClient;
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
    public Product saveProduct(Product product)
    {       Product prd=repo.save(product);
     try {
        producerClient.sendMessage("PC_PRODUCTVERIFICATION",String.valueOf(prd.getId()), objectMapper.writeValueAsString(prd));

    }catch(JsonProcessingException e){
        throw new RuntimeException(e.getMessage());
    }

        return prd;
    }

    @Override
    public Page<Product> fetchProductsByCategory(String category, RecordState state, int pageNumber, int pageSize, SortingCriteria sortCriteria) {
        Sort sort=sortFactory.getSortObject(sortCriteria);
        Pageable pageable= PageRequest.of( pageNumber, pageSize,sort);

        return repo.getProductsByCategory_TitleAndState(category,state,pageable);
    }

    @Override
    public Product fetchProductById(Long prodId) throws NoProductsFoundException {
        Product product;
        product=(Product)redisTemplate.opsForHash().get("PRODUCT","PRODUCT_"+prodId);
        if(product!=null)return product;
        Optional<Product> optional=repo.findById(prodId);
        if(optional.isPresent()) {
            product = optional.get();
        }else{
            throw new NoProductsFoundException("Product not found");
        }
        redisTemplate.opsForHash().put("PRODUCT","PRODUCT_"+prodId,product);

        return product;
    }
}
