package sit.int204.classicmodelsservice.dtos;

import lombok.Getter;
import lombok.Setter;
import sit.int204.classicmodelsservice.entities.Office;

@Getter
@Setter
public class SimpleEmployeeDTO {
    private String firstName;
    private String lastName;
    private String officeCity;
    public String getName(){
        return firstName + " " + lastName;
    }

}
