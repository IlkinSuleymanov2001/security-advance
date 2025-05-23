package com.example.demo.service.impl

import com.example.demo.dao.entity.ProductEntity
import com.example.demo.dao.repository.ProductRepository
import com.example.demo.exception.NotFoundException
import com.example.demo.mapper.ProductMapper
import com.example.demo.model.criteria.PageCriteria
import com.example.demo.model.criteria.ProductCriteria
import com.example.demo.model.enums.ErrorInfo
import com.example.demo.model.request.product.ASingOrGetProductIdentifier
import com.example.demo.service.impl.inter.ProductService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spock.lang.Specification

class ProductServiceHandlerTest extends Specification {

    ProductRepository productRepository = Mock()
    ProductMapper productMapper = Mock()
    ProductService productService
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def setup() {
        productService = new ProductServiceHandler(productRepository, productMapper)
    }

    def "getProductByTest success case,find product with id"() {

        given:

        def singOrGetProductIdentifier = random.nextObject(ASingOrGetProductIdentifier)
        def productEntity = random.nextObject(ProductEntity)
//        productRepository.existsById(singOrGetProductIdentifier.getProductId()) >> true

        when:
        productService.getProductBy(singOrGetProductIdentifier)

        then:
        1 * productRepository.existsById(singOrGetProductIdentifier.getProductId()) >> true
        1 * productRepository.findById(singOrGetProductIdentifier.getProductId()) >> Optional.of(productEntity)


    }

    def "getProductByTest success case, find product with productCode "() {

        given:

        def singOrGetProductIdentifier = random.nextObject(ASingOrGetProductIdentifier)
        def productEntity = random.nextObject(ProductEntity)

        when:
        productService.getProductBy(singOrGetProductIdentifier)

        then:
        1 * productRepository.existsById(singOrGetProductIdentifier.getProductId()) >> false
        1 * productRepository.findByProductCode(singOrGetProductIdentifier.getProductCode()) >> Optional.of(productEntity)


    }

    def "getProductByTest error case,exists product id in request but invalid and  product code is null "() {

        given:

        def singOrGetProductIdentifier = random.nextObject(ASingOrGetProductIdentifier)
        singOrGetProductIdentifier.setProductCode(null)

        when:
        productService.getProductBy(singOrGetProductIdentifier)

        then:
        def e = thrown(NotFoundException)
        e.message == ErrorInfo.PRODUCT_NOT_FOUND.message
        e.errorCode == ErrorInfo.PRODUCT_NOT_FOUND.errorCode

    }

    def "getProductByTest error case,product code and id  is null "() {

        given:

        def singOrGetProductIdentifier = random.nextObject(ASingOrGetProductIdentifier)
        singOrGetProductIdentifier.setProductCode(null)
        singOrGetProductIdentifier.setProductId(null)

        when:
        productService.getProductBy(singOrGetProductIdentifier)

        then:
        def e = thrown(NotFoundException)
        e.message == ErrorInfo.PRODUCT_NOT_FOUND.message
        e.errorCode == ErrorInfo.PRODUCT_NOT_FOUND.errorCode

    }

    def "getProductByTest success case 2, find product with productCode "() {

        given:

        def singOrGetProductIdentifier = random.nextObject(ASingOrGetProductIdentifier)
        singOrGetProductIdentifier.setProductId(null)

        def productEntity = random.nextObject(ProductEntity)

        when:
        productService.getProductBy(singOrGetProductIdentifier)

        then:
        1 * productRepository.findByProductCode(singOrGetProductIdentifier.getProductCode()) >> Optional.of(productEntity)

    }

    def "getProductByTest error case ,exists product code in request but invalid and  product id is null "() {

        given:

        def singOrGetProductIdentifier = random.nextObject(ASingOrGetProductIdentifier)
        singOrGetProductIdentifier.setProductId(null)

        when:
        productService.getProductBy(singOrGetProductIdentifier)

        then:
        1 * productRepository.findByProductCode(singOrGetProductIdentifier.getProductCode()) >> Optional.empty()
        def e = thrown(NotFoundException)
        e.message == ErrorInfo.PRODUCT_NOT_FOUND.message
        e.errorCode == ErrorInfo.PRODUCT_NOT_FOUND.errorCode
    }

    def "getAllProductTest success case "() {

        given:
        def pageCrt = random.nextObject(PageCriteria)
        def productCrt = random.nextObject(ProductCriteria)

        def pageable = PageRequest.of(pageCrt.getPage(),
                pageCrt.getCount(),
                Sort.by("id").descending())


//        def page = random.nextObject(org.springframework.data.domain.Page)
        def page = Mock(org.springframework.data.domain.Page)
        page.getContent() >> [random.nextObject(ProductEntity), random.nextObject(ProductEntity)]
        page.getTotalElements() >> 2
        page.getTotalPages() >> 1

        when:
        productService.getAllProduct(pageCrt, productCrt)

        then:
        1 * productRepository.findAll(_, pageable) >> page
        1 * productMapper.toPageableProductResponse(page)


    }


}
