package br.com.backend.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {
    @jakarta.validation.constraints.NotBlank(message = "Street is required")
    private String street;

    @jakarta.validation.constraints.NotBlank(message = "Number is required")
    private String number;

    private String complement;

    @jakarta.validation.constraints.NotBlank(message = "CEP is required")
    @jakarta.validation.constraints.Pattern(regexp = "\\d{8}", message = "CEP must be 8 digits")
    private String cep;

    @jakarta.validation.constraints.NotBlank(message = "Neighborhood is required")
    private String neighborhood;

    @jakarta.validation.constraints.NotBlank(message = "City name is required")
    private String cityName;

    @jakarta.validation.constraints.NotBlank(message = "State name is required")
    private String stateName;
}
