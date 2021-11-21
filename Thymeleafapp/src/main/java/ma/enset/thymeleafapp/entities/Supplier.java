package ma.enset.thymeleafapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Supplier {
    private Long id;
    private String name;
    private String email;
}
