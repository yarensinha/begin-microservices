package com.techie.microservices.productservice.controller;

import com.techie.microservices.productservice.model.Product;
import com.techie.microservices.productservice.repository.ProductRepository;
import com.techie.microservices.productservice.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final SequenceGeneratorService sequenceGeneratorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product){
        product.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
        productRepository.save(product);

    }

}
