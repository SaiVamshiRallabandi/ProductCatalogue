package com.ecom.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ExceptionDto {
    private String errorCode;
    private String errorDescription;
}
