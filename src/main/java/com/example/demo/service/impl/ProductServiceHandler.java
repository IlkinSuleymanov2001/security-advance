package com.example.demo.service.impl;

import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.repository.ProductRepository;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.criteria.PageCriteria;
import com.example.demo.model.criteria.ProductCriteria;
import com.example.demo.model.request.product.ASingOrGetProductIdentifier;
import com.example.demo.model.request.product.CreateProductRequest;
import com.example.demo.model.response.ProductResponse;
import com.example.demo.model.response.page.PageableResponse;
import com.example.demo.service.impl.inter.ProductService;
import com.example.demo.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.PRODUCT_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;


@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceHandler implements ProductService {


    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        ProductEntity product = productRepository.save(productMapper.toEntity(createProductRequest));
        return productMapper.toResponse(product);
    }

    @Override
    public ProductEntity getProductBy(ASingOrGetProductIdentifier identifier) {
        if (identifier.getProductId() != null) {
            return productRepository.findById(identifier.getProductId())
                    .orElseThrow(() -> EXCEPTION.notFoundThrowable(PRODUCT_NOT_FOUND));
        }

        if (identifier.getProductCode() != null) {
            return productRepository.findByProductCode(identifier.getProductCode())
                    .orElseThrow(() -> EXCEPTION.notFoundThrowable(PRODUCT_NOT_FOUND));
        }

        throw EXCEPTION.notFoundThrowable(PRODUCT_NOT_FOUND);
    }


    @Override
    public ProductResponse getProduct(ASingOrGetProductIdentifier aSingOrGetProductIdentifier) {
        ProductEntity productBy = getProductBy(aSingOrGetProductIdentifier);
        return productMapper.toResponse(productBy);
    }

    @Override
    public PageableResponse<ProductResponse> getAllProduct(PageCriteria pageCriteria, ProductCriteria productCriteria) {

        PageRequest pageable = PageRequest.of(pageCriteria.getPage(),
                pageCriteria.getCount(),
                Sort.by("id").descending());

        Page<ProductEntity> productEntities = productRepository.findAll(new ProductSpecification(productCriteria), pageable);

        return productMapper.toPageableProductResponse(productEntities);
    }
}
