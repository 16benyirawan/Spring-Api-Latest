package id.beny.spring_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.beny.spring_api.dto.ProductData;
import id.beny.spring_api.dto.ResponseData;
import id.beny.spring_api.dto.SearchData;
import id.beny.spring_api.model.entities.Product;
import id.beny.spring_api.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    

    @PostMapping("/save")
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody ProductData productData, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        List<String> listmessage = new ArrayList<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Product product = modelMapper.map(productData, Product.class);

        Product newProduct = productService.save(productData);
        if (newProduct != null) {
            responseData.setStatus(true);
            responseData.setPayload(newProduct);
            listmessage.add("Data berhasil di simpan");
            responseData.setMessage(listmessage);

            return ResponseEntity.ok(responseData);
        }
        
        responseData.setStatus(false);
        responseData.setPayload(null);
        listmessage.add("Data gagal di simpan");
        responseData.setMessage(listmessage);


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);       
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/find/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping("/edit/")
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody ProductData productData, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        Product updatedProduct = productService.save(productData);
        if (updatedProduct != null) {
            responseData.setStatus(true);
            responseData.setPayload(updatedProduct);

            return ResponseEntity.ok(responseData);
        }
        
        responseData.setStatus(false);
        responseData.setPayload(null);
        // responseData.setMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData); 
    }

    @DeleteMapping("/delete/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    // @PostMapping("/addsupplier/{id}")
    // public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
    //     productService.addSupplier(supplier, productId);
    // }

    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){
        return productService.findByProductName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List<Product> getProductByNameLike(@RequestBody SearchData searchData){
        return productService.findByProductNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findByProductCategory(categoryId);
    }
}