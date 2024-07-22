package id.beny.spring_api.model.repo;

import org.springframework.data.repository.CrudRepository;

import id.beny.spring_api.model.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{

}
