package ma.enset.supplierservice.repositories;

import ma.enset.supplierservice.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
