package id.beny.spring_api.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import id.beny.spring_api.model.entities.Product;
import jakarta.websocket.server.PathParam;

public interface ProductRepo extends CrudRepository<Product, Long>{

    List<Product> findByNameContains(String name);

    @Query("SELECT p from Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p Where p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId")Long categoryId);
}
