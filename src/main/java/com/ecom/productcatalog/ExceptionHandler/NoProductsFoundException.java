package com.ecom.productcatalog.ExceptionHandler;

public class NoProductsFoundException extends Exception{
    public NoProductsFoundException(String exception){
        super(exception);
    }
}
