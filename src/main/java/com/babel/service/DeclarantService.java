package com.babel.service;

import com.babel.dao.IDeclarantRepository;
import com.babel.dto.DeclarantDto;
import com.babel.entities.Declarant;
import com.babel.exception.EntityNotFoundException;
import com.babel.mapping.DeclarantMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeclarantService {

    private IDeclarantRepository iDeclarantRepository;
    private DeclarantMapper declarantMapper;
    MessageSource messageSource;

    public DeclarantService(IDeclarantRepository iDeclarantRepository, DeclarantMapper declarantMapper, MessageSource messageSource) {
        this.iDeclarantRepository = iDeclarantRepository;
        this.declarantMapper = declarantMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public Declarant getDeclarant(Long id) {
        return iDeclarantRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("declarant.notfound", new Object[]{id},
                                Locale.getDefault())));
    }

    @Transactional(readOnly = true)
    public List<DeclarantDto> getAllDeclarants() {
        return StreamSupport.stream(iDeclarantRepository.findAll().spliterator(), false)
                .map(declarantMapper::toDeclarant)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeclarantDto createDeclarant(DeclarantDto declarantDto) {
        return declarantMapper.toDeclarant(iDeclarantRepository.save(declarantMapper.fromDeclarant(declarantDto)));
    }

    @Transactional(readOnly = true)
    public Declarant findByEmail(String email) {
        return iDeclarantRepository.findByCode(email);
    }
}
