package com.example.demo.mapper;

import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.model.request.product.CreateProductRequest;
import com.example.demo.model.response.ProductResponse;
import com.example.demo.model.response.page.PageableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "mark", target = "mark")
    ProductResponse toResponse(ProductEntity productEntity);

    @Mapping(source = "productCode", target = "productCode")
    @Mapping(target = "mark", expression = "java(request.getMark().getBrandName())")
    ProductEntity toEntity(CreateProductRequest request);


    default PageableResponse<ProductResponse> toPageableProductResponse(Page<ProductEntity> productEntities) {

        PageableResponse<ProductResponse> pageableResponse = new PageableResponse();
        pageableResponse.setTotalElements(productEntities.getTotalElements());
        pageableResponse.setData(productEntities.stream().map(this::toResponse).toList());
        pageableResponse.setHasNextPage(productEntities.hasNext());
        pageableResponse.setLastPageNumber(productEntities.getNumber());

        return pageableResponse;
    }

}
