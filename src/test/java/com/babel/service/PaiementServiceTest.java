package com.babel.service;

import com.babel.dao.IPaiementRepository;
import com.babel.dto.PaiementDto;
import com.babel.entities.Paiement;
import com.babel.mapping.PaiementMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaiementServiceTest {

    @Mock
    private IPaiementRepository iPaiementRepository;

    @Mock
    private PaiementMapper paiementMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private PaiementService paiementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPaiements() {
        // Mocking data
        List<Paiement> paiements = new ArrayList<>();
        paiements.add(new Paiement());
        when(iPaiementRepository.findAll()).thenReturn(paiements);

        // Call the method to test
        paiementService.getAllPaiements();

        // Verify that the findAll method of iPaiementRepository is called
        verify(iPaiementRepository, times(1)).findAll();
    }

    @Test
    public void testCreatePaiement() {
        // Mocking data
        PaiementDto paiementDto = new PaiementDto();
        Paiement paiement = new Paiement();
        when(paiementMapper.fromPaiement(paiementDto)).thenReturn(paiement);
        when(iPaiementRepository.save(paiement)).thenReturn(paiement);

        // Call the method to test
        paiementService.createPaiement(paiementDto);

        // Verify that the fromPaiement and save methods are called
        verify(paiementMapper, times(1)).fromPaiement(paiementDto);
        verify(iPaiementRepository, times(1)).save(paiement);
    }

    @Test
    public void testGetPaiementByDeclaration() {
        // Mocking data
        Long declarationId = 1L;
        List<Paiement> paiements = new ArrayList<>();
        when(iPaiementRepository.findPaiementByDeclaration(declarationId)).thenReturn(paiements);

        // Call the method to test
        paiementService.getPaiementByDeclaration(declarationId);

        // Verify that the findPaiementByDeclaration method of iPaiementRepository is called
        verify(iPaiementRepository, times(1)).findPaiementByDeclaration(declarationId);
    }
}
