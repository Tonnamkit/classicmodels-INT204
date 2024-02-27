package sit.int204.classicmodelsservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import sit.int204.classicmodelsservice.entities.Employee;

@Getter
@Setter
public class SimpleCustomerDTO {
    private String customerName;
    private String phone;
    private String city;
    private String country;
//    private Employee sales;
//    private String salesOfficePhone;
    @JsonIgnore
    private SimpleEmployeeDTO sales;
    public String getSalesPerson(){
        return sales == null ? "-" : sales.getName() + " - " + sales.getOfficeCity();
    }
}
