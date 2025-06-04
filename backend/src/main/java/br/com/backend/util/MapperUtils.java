package br.com.backend.util;

import br.com.backend.dto.request.AddressRequestDTO;
import br.com.backend.dto.request.PersonRequestDTO;
import br.com.backend.dto.response.AddressResponseDTO;
import br.com.backend.dto.response.PersonResponseDTO;
import br.com.backend.entity.Address;
import br.com.backend.entity.Person;
import org.springframework.beans.BeanUtils;

/**
 * Utility class for mapping between different object types.
 * This class can be used to convert between DTOs and entities or other types.
 */
public class MapperUtils {
    public static PersonResponseDTO mapToPersonResponseDTO(Person person) {
        PersonResponseDTO dto = new PersonResponseDTO();
        BeanUtils.copyProperties(person, dto);
        if (person.getAddress() != null) {
            AddressResponseDTO addressDto = new AddressResponseDTO();
            BeanUtils.copyProperties(person.getAddress(), addressDto);
            dto.setAddress(addressDto);
        }
        return dto;
    }

    public static Person mapToPerson(PersonRequestDTO dto) {
        Person person = new Person();
        BeanUtils.copyProperties(dto, person, "address");
        if (dto.getAddress() != null) {
            Address address = new Address();
            BeanUtils.copyProperties(dto.getAddress(), address);
            person.setAddress(address);
        }
        return person;
    }

    public static Address mapToAddress(AddressRequestDTO dto) {
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        return address;
    }

    public static AddressResponseDTO mapToAddressResponseDTO(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        BeanUtils.copyProperties(address, dto);
        return dto;
    }
}
