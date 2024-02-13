package sit.int204.classicmodelsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProductLine {
    @Id
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;
    @JsonIgnore
    @OneToMany(mappedBy = "productLine")
    private List<Product> productList;

}
