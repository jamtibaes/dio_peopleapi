package com.digitalinnovationone.personapi.utils;

import com.digitalinnovationone.personapi.dto.request.PersonDTO;
import com.digitalinnovationone.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

    private static final String FIRST_NAME = "José";
    private static final String LAST_NAME = "Tibaes";
    private static final String CPF_NUMBER = "285.131.718-08";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(1981, 7, 3);

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate("03-07-1981")
                .phones(Collections.singletonList(com.digitalinnovationone.personapi.utils.PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(com.digitalinnovationone.personapi.utils.PhoneUtils.createFakeEntity()))
                .build();
    }
}
