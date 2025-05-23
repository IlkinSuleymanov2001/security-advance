package com.example.demo.service.impl.inter;

import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.model.criteria.PageCriteria;
import com.example.demo.model.criteria.ProductCriteria;
import com.example.demo.model.request.product.ASingOrGetProductIdentifier;
import com.example.demo.model.request.product.CreateProductRequest;
import com.example.demo.model.response.ProductResponse;
import com.example.demo.model.response.page.PageableResponse;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest createProductRequest);

    ProductEntity getProductBy(ASingOrGetProductIdentifier aSingOrGetProductIdentifier);

    ProductResponse getProduct(ASingOrGetProductIdentifier aSingOrGetProductIdentifier);

    PageableResponse<ProductResponse> getAllProduct(PageCriteria pageCriteria, ProductCriteria productCriteria);


}
