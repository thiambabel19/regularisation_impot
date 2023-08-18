package com.babel.service;

import com.babel.dao.IPaiementRepository;
import com.babel.dto.PaiementDto;
import com.babel.entities.Paiement;
import com.babel.mapping.PaiementMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaiementService {

    private IPaiementRepository iPaiementRepository;
    private PaiementMapper paiementMapper;
    MessageSource messageSource;

    public PaiementService(IPaiementRepository iPaiementRepository, PaiementMapper paiementMapper, MessageSource messageSource) {
        this.iPaiementRepository = iPaiementRepository;
        this.paiementMapper = paiementMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<PaiementDto> getAllPaiements() {
        return StreamSupport.stream(iPaiementRepository.findAll().spliterator(), false)
                .map(paiementMapper::toPaiement)
                .collect(Collectors.toList());
    }

    @Transactional
    public PaiementDto createPaiement(PaiementDto paiementDto) {
        return paiementMapper.toPaiement(iPaiementRepository.save(paiementMapper.fromPaiement(paiementDto)));
    }

    @Transactional(readOnly = true)
    public List<Paiement> getPaiementByDeclaration(Long id){
        return iPaiementRepository.findPaiementByDeclaration(id);
    }

}
