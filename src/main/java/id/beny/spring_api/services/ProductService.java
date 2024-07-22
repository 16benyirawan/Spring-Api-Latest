package id.beny.spring_api.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.beny.spring_api.dto.ProductData;
import id.beny.spring_api.model.entities.Category;
import id.beny.spring_api.model.entities.Product;
import id.beny.spring_api.model.repo.CategoryRepo;
import id.beny.spring_api.model.repo.ProductRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Product save(ProductData productData){

        Product product;

        if (productData.getId() == null){
            product = modelMapper.map(productData, Product.class);

            Optional<Category> category = categoryRepo.findById(productData.getCategoryId());
            if (!category.isPresent()) {
             return null;    
            }
            
            product.setCategory(category.get());
            return productRepo.save(product); 
        } 

        Optional<Product> existingProduct = productRepo.findById(productData.getId());
        if (existingProduct.isPresent()) {
            product = existingProduct.get();
            product.setName(productData.getName());
            product.setDescription(productData.getDescription());
            product.setPrice(productData.getPrice());    
            return productRepo.save(product);
        }
        
        return null;

        
    }

    public Product findOne(Long id){
        Optional<Product> product = productRepo.findById(id);

        if (!product.isPresent()){
            return null;
        }
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    // public void addSupplier(Supplier supplier, Long productId){
    //     Product product = findOne(productId);

    //     if (product == null) {
    //         throw new RuntimeException("Product with ID"+ productId + "not found.");
    //     }
    //     product.getSuppliers().add(supplier);
    //     save(product);
    // }

    public Product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepo.findProductByNameLike("%"+ name +"%");
    }

    public List<Product> findByProductCategory(Long categoryId){
        return productRepo.findProductByCategory(categoryId);
    }

}
