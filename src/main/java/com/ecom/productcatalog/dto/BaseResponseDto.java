package com.ecom.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Getter
@Setter
public class BaseResponseDto {
    private ResponseStatus status;
    private List<ExceptionDto> exceptions;
}
