package br.com.backend.service;

import br.com.backend.dto.request.PersonRequestDTO;
import br.com.backend.dto.response.PersonResponseDTO;
import br.com.backend.entity.Person;
import br.com.backend.repository.PersonRepository;
import br.com.backend.util.MapperUtils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Person entities.
 * This class contains business logic related to Person operations.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponseDTO> findAll() {
        return personRepository.findAll().stream()
                .map(person -> MapperUtils.mapToPersonResponseDTO(person))
                .collect(Collectors.toList());
    }

    @Transactional
    public PersonResponseDTO save(PersonRequestDTO request) {
        Person person = MapperUtils.mapToPerson(request);
        person = personRepository.save(person);

        return MapperUtils.mapToPersonResponseDTO(person);
    }
}
