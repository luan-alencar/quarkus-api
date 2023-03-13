package dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private Integer age;

}
