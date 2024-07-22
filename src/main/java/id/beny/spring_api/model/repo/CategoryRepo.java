package id.beny.spring_api.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.beny.spring_api.model.entities.Category;

public interface  CategoryRepo extends JpaRepository<Category, Long>{

    Page<Category> findByNameContains(String name, Pageable pageable);
}
