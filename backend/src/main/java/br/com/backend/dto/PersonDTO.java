package br.com.backend.dto;

import br.com.backend.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for Person.
 * This class is used to transfer data about a Person entity
 * between the server and the client.
 * It includes fields for the person's details and timestamps
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String phone;
    private String cpf;
    private Address address;
}
