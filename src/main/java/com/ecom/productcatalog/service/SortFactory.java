package com.ecom.productcatalog.service;

import com.ecom.productcatalog.dto.SortingCriteria;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortFactory {
    public Sort getSortObject(SortingCriteria sortingCriteria) {
        switch (sortingCriteria) {
            case PRICE_HIGH_LOW:
                return Sort.by(Sort.Direction.DESC, "price");

            case PRICE_LOW_HIGH:
                return Sort.by(Sort.Direction.ASC, "price");

        }
        return null;
    }
}
