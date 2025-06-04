package br.com.backend.controller;

import br.com.backend.dto.request.PersonRequestDTO;
import br.com.backend.dto.response.PersonResponseDTO;
import br.com.backend.service.PersonService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResponseDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public PersonResponseDTO save(@Valid @RequestBody PersonRequestDTO request) {
        return personService.save(request);
    }
}
