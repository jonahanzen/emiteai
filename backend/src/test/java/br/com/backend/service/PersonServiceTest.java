package br.com.backend.service;

import br.com.backend.dto.request.PersonRequestDTO;
import br.com.backend.dto.response.PersonResponseDTO;
import br.com.backend.entity.Person;
import br.com.backend.repository.PersonRepository;
import br.com.backend.util.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Person person = new Person();
        person.setName("John Doe");
        when(personRepository.findAll()).thenReturn(Arrays.asList(person));
        List<PersonResponseDTO> result = personService.findAll();
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testSave() {
        PersonRequestDTO request = new PersonRequestDTO();
        request.setName("Jane Doe");
        Person person = MapperUtils.mapToPerson(request);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        PersonResponseDTO response = personService.save(request);
        assertEquals("Jane Doe", response.getName());
    }
}
