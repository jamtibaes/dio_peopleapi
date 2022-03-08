package com.digitalinnovationone.personapi.service;

import com.digitalinnovationone.personapi.dto.request.PersonDTO;
import com.digitalinnovationone.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personapi.entity.Person;
import com.digitalinnovationone.personapi.exception.PersonNotFoundException;
import com.digitalinnovationone.personapi.mapper.PersonMapper;
import com.digitalinnovationone.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

  /*  @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/

    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        /*Person person = Person
                .builder()
                .firstName(personDTO.getFirstName())
                .build();*/

        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        // Usar pesquisa dessa forma
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty()) {
            throw new PersonNotFoundException(id);
        }

        return personMapper.toDTO(optionalPerson.get());
    }

    public void delete(Long id) throws PersonNotFoundException {
        // Usar pesquisa dessa outra forma
        personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateByID(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToUpdate);

        return MessageResponseDTO
                .builder()
                .message("Updated person with ID" + savedPerson.getId())
                .build();
    }

    private void verifyIfExists(Long id) throws PersonNotFoundException {
        personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
    }


}
