package com.digitalinnovation.springdatacpf.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {
    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Zip Code is required!")
    private String zipCode;
}
