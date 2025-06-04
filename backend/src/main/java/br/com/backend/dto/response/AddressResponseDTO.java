package br.com.backend.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
    private Long id;
    private String uuid;
    private String street;
    private String number;
    private String complement;
    private String cep;
    private String neighborhood;
    private String cityName;
    private String stateName;
}
