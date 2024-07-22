package id.beny.spring_api.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import id.beny.spring_api.model.entities.User;

public interface  UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
