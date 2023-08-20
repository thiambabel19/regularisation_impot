package com.babel.service;

import com.babel.dao.IDeclarationRepository;
import com.babel.dto.DeclarationDto;
import com.babel.entities.Declarant;
import com.babel.entities.Declaration;
import com.babel.exception.EntityNotFoundException;
import com.babel.mapping.DeclarationMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeclarationService {

    private IDeclarationRepository iDeclarationRepository;
    private DeclarationMapper declarationMapper;
    MessageSource messageSource;

    public DeclarationService(IDeclarationRepository iDeclarationRepository, DeclarationMapper declarationMapper, MessageSource messageSource) {
        this.iDeclarationRepository = iDeclarationRepository;
        this.declarationMapper = declarationMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<DeclarationDto> getAllDeclarations() {
        return StreamSupport.stream(iDeclarationRepository.findAll().spliterator(), false)
                .map(declarationMapper::toDeclaration)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeclarationDto createDeclaration(DeclarationDto declarationDto) {
        return declarationMapper.toDeclaration(iDeclarationRepository.save(declarationMapper.fromDeclaration(declarationDto)));
    }

    @Transactional(readOnly = true)
    public Declaration getDeclarationById(Long id) {
        return iDeclarationRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("declaration.notfound", new Object[]{id},
                                Locale.getDefault())));
    }

    @Transactional(readOnly = true)
    public Declaration getDeclaration(Long id) {
        return iDeclarationRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("declaration.notfound", new Object[]{id},
                                Locale.getDefault())));
    }

    @Transactional
    public DeclarationDto updateDeclaration(Long id, DeclarationDto declarationDto) {
        return iDeclarationRepository.findById(id)
                .map(entity -> {
                    declarationDto.setId(id);
                    return declarationMapper.toDeclaration(
                            iDeclarationRepository.save(declarationMapper.fromDeclaration(declarationDto)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("declaration.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional(readOnly = true)
    public List<Declaration> getDeclarationNonReglee() {
        return iDeclarationRepository.getDeclarationNonReglee();
    }

}
