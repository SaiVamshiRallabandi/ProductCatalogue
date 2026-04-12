package com.ecom.productcatalog.ExceptionHandler;

import com.ecom.productcatalog.dto.ExceptionDto;
import com.ecom.productcatalog.dto.ResponseStatus;
import com.ecom.productcatalog.dto.SearchProductsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<SearchProductsResponseDto> noProductsFoundException(NoProductsFoundException e){
        SearchProductsResponseDto rspDto=new SearchProductsResponseDto();
        rspDto.setStatus(ResponseStatus.FAILURE);

        rspDto.setExceptions(List.of(new ExceptionDto("001","No records found")));
        return ResponseEntity.ok().body(rspDto);
    }
}
