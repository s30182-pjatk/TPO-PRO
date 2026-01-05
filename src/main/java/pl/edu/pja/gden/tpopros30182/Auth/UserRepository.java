package pl.edu.pja.gden.tpopros30182.Auth;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findAllUsersByRoles_Name(String role);
    void deleteByEmail(String email);
    void deleteById(Long id);
}
