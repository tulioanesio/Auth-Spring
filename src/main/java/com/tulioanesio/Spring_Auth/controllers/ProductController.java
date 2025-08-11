package com.tulioanesio.Spring_Auth.controllers;

import com.tulioanesio.Spring_Auth.model.product.Product;
import com.tulioanesio.Spring_Auth.model.product.dtos.ProductDTO;
import com.tulioanesio.Spring_Auth.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping()
    public ResponseEntity createProduct(@RequestBody ProductDTO dto) {
        var product = new Product();
        BeanUtils.copyProperties(dto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        List<Product> listProducts = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductDTO dto){
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        var productModel = product.get();
        BeanUtils.copyProperties(dto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        repository.delete(product.get());

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
