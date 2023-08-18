package com.babel.service;

import com.babel.dao.IDeclarationRepository;
import com.babel.dto.DeclarationDto;
import com.babel.entities.Declaration;
import com.babel.exception.EntityNotFoundException;
import com.babel.mapping.DeclarationMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeclarationServiceTest {

    @Mock
    private IDeclarationRepository declarationRepository;

    @Mock
    private DeclarationMapper declarationMapper;

    @Mock
    private MessageSource messageSource;

    private DeclarationService declarationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        declarationService = new DeclarationService(declarationRepository, declarationMapper, messageSource);
    }

    @Test
    public void testGetAllDeclarations() {
        // Mocking data
        Declaration declaration1 = new Declaration();
        Declaration declaration2 = new Declaration();
        List<Declaration> declarations = Arrays.asList(declaration1, declaration2);

        when(declarationRepository.findAll()).thenReturn(declarations);

        // Mocking mapping
        DeclarationDto dto1 = new DeclarationDto();
        DeclarationDto dto2 = new DeclarationDto();
        when(declarationMapper.toDeclaration(declaration1)).thenReturn(dto1);
        when(declarationMapper.toDeclaration(declaration2)).thenReturn(dto2);

        // Call the service method
        List<DeclarationDto> result = declarationService.getAllDeclarations();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));

        // Verify interactions
        verify(declarationRepository, times(1)).findAll();
        verify(declarationMapper, times(2)).toDeclaration(any(Declaration.class));
    }

    @Test
    public void testCreateDeclaration() {
        // Mocking data
        DeclarationDto inputDto = new DeclarationDto();
        Declaration declaration = new Declaration();

        when(declarationMapper.fromDeclaration(inputDto)).thenReturn(declaration);
        when(declarationRepository.save(declaration)).thenReturn(declaration);

        // Mocking mapping
        DeclarationDto outputDto = new DeclarationDto();
        when(declarationMapper.toDeclaration(declaration)).thenReturn(outputDto);

        // Call the service method
        DeclarationDto result = declarationService.createDeclaration(inputDto);

        // Verify the result
        assertEquals(outputDto, result);

        // Verify interactions
        verify(declarationMapper, times(1)).fromDeclaration(inputDto);
        verify(declarationRepository, times(1)).save(declaration);
        verify(declarationMapper, times(1)).toDeclaration(declaration);
    }

    @Test
    public void testGetDeclaration() {
        Long id = 1L;
        Declaration declaration = new Declaration();
        when(declarationRepository.findById(id)).thenReturn(Optional.of(declaration));

        Declaration result = declarationService.getDeclaration(id);

        assertEquals(declaration, result);
        verify(declarationRepository, times(1)).findById(id);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetDeclarationNotFound() {
        Long id = 1L;
        when(declarationRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("declaration.notfound"), any(Object[].class), any(Locale.class)))
                .thenReturn("Declaration not found");

        declarationService.getDeclaration(id);
    }

    @Test
    public void testUpdateDeclaration() {
        Long id = 1L;
        DeclarationDto inputDto = new DeclarationDto();
        Declaration existingDeclaration = new Declaration();

        when(declarationRepository.findById(id)).thenReturn(Optional.of(existingDeclaration));

        // Mocking mapping
        Declaration updatedDeclaration = new Declaration();
        when(declarationMapper.fromDeclaration(inputDto)).thenReturn(updatedDeclaration);
        when(declarationRepository.save(updatedDeclaration)).thenReturn(updatedDeclaration);
        when(declarationMapper.toDeclaration(updatedDeclaration)).thenReturn(inputDto);

        // Call the service method
        DeclarationDto result = declarationService.updateDeclaration(id, inputDto);

        // Verify the result
        assertEquals(inputDto, result);

        // Verify interactions
        verify(declarationRepository, times(1)).findById(id);
        verify(declarationMapper, times(1)).fromDeclaration(inputDto);
        verify(declarationRepository, times(1)).save(updatedDeclaration);
        verify(declarationMapper, times(1)).toDeclaration(updatedDeclaration);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdateDeclarationNotFound() {
        Long id = 1L;
        DeclarationDto inputDto = new DeclarationDto();

        when(declarationRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("declaration.notfound"), any(Object[].class), any(Locale.class)))
                .thenReturn("Declaration not found");

        declarationService.updateDeclaration(id, inputDto);
    }
}
