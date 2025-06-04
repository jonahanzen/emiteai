package br.com.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Person Request.
 * This class is used to transfer data from the client to the server
 * when creating or updating a Person entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10,11}", message = "Phone must be 10 or 11 digits")
    private String phone;

    @NotBlank(message = "CPF is required")
    @Pattern(regexp = "\\d{11}", message = "CPF must be 11 digits")
    private String cpf;

    private AddressRequestDTO address;
}
