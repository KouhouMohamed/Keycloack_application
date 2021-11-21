package ma.enset.thymeleafapp.repositories;

import ma.enset.thymeleafapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
