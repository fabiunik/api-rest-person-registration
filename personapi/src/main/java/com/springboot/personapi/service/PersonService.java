package com.springboot.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.personapi.dto.request.PersonDTO;
import com.springboot.personapi.dto.response.MessageResponseDTO;
import com.springboot.personapi.entity.Person;
import com.springboot.personapi.exception.PersonNotFoundException;
import com.springboot.personapi.mapper.PersonMapper;
import com.springboot.personapi.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class PersonService {
    
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    
    
    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMethodResponde(savedPerson.getId(), "created person with ID");

        
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException { 
        Person person = verifyIfExists(id);
      
        return personMapper.toDTO(person);
    }


    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
        
    }

    private Person verifyIfExists (Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
      
    }


    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);

        return createMethodResponde(updatedPerson.getId(), "Updated person with ID");
    }


    private MessageResponseDTO createMethodResponde(Long id, String s) {
        return MessageResponseDTO.builder()
        .message(s + id)
        .build();
    }



    


    
}