package ma.enset.supplierservice.web;

import ma.enset.supplierservice.entities.Supplier;
import ma.enset.supplierservice.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

//@Controller
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping(path = "/suppliers", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<Supplier> suppliers() {
        return supplierRepository.findAll();
    }

}

