package sit.int204.classicmodelsservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Id
    private String id;
    private String name;
    private Double score;
    private Character grade;
}
