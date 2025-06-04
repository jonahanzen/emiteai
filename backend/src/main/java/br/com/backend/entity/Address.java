package br.com.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address extends AbstractEntityCreatedUpdated {
    private String street;
    private String number;
    private String complement;
    private String cep;
    private String neighborhood;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "state_name")
    private String stateName;
}
