package com.babel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import com.babel.dao.IDeclarantRepository;
import com.babel.dto.DeclarantDto;
import com.babel.entities.Declarant;
import com.babel.exception.EntityNotFoundException;
import com.babel.mapping.DeclarantMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DeclarantServiceTest {

    @Mock
    private IDeclarantRepository declarantRepository;

    @Mock
    private DeclarantMapper declarantMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private DeclarantService declarantService;

    // Executer avant les tests et initialise les mocks
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Teste si la méthode getDeclarant fonctionne correctement lorsque l'ID existe dans le repository.
    @Test
    public void testGetDeclarantById() {
        Long declarantId = 1L;
        Declarant declarant = new Declarant();
        when(declarantRepository.findById(declarantId)).thenReturn(Optional.of(declarant));

        Declarant result = declarantService.getDeclarant(declarantId);

        assertNotNull(result);
        assertEquals(declarant, result);
    }

    // Un test pour vérifier si la méthode getDeclarant génère bien une exception
    @Test(expected = EntityNotFoundException.class)
    public void testGetDeclarantByIdNotFound() {
        Long declarantId = 1L;
        when(declarantRepository.findById(declarantId)).thenReturn(Optional.empty());

        declarantService.getDeclarant(declarantId);
    }

    // Un test pour vérifier si la méthode getAllDeclarants renvoie la liste attendue de déclarants.
    @Test
    public void testGetAllDeclarants() {
        List<Declarant> declarantList = new ArrayList<>();
        declarantList.add(new Declarant());
        declarantList.add(new Declarant());
        when(declarantRepository.findAll()).thenReturn(declarantList);

        List<DeclarantDto> result = declarantService.getAllDeclarants();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    // Un test pour vérifier si la méthode createDeclarant fonctionne correctement.
    @Test
    public void testCreateDeclarant() {
        DeclarantDto declarantDto = new DeclarantDto();
        declarantDto.setRaisonSociale("kjfs");
        declarantDto.setAdresse("pk");
        declarantDto.setEmail("bbb@gmail.com");
        declarantDto.setTelephone("772733300");

        DeclarantDto declarantDtoSave = declarantService.createDeclarant(declarantDto);

        Assertions.assertEquals("kjfs", declarantDtoSave.getRaisonSociale());
        Assertions.assertEquals("pk", declarantDtoSave.getAdresse());
        Assertions.assertEquals("bbb@gmail.com", declarantDtoSave.getEmail());
        Assertions.assertEquals("772733300", declarantDtoSave.getTelephone());
        Assertions.assertNotNull(declarantDtoSave);
    }

    // Un test pour vérifier si la méthode findByEmail renvoie le déclarant attendu en fonction de l'adresse e-mail.
    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Declarant declarant = new Declarant();
        when(declarantRepository.findByCode(email)).thenReturn(declarant);

        Declarant result = declarantService.findByEmail(email);

        assertNotNull(result);
        assertEquals(declarant, result);
    }
}
