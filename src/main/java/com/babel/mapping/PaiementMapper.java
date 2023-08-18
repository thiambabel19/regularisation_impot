package com.babel.mapping;

import com.babel.dto.PaiementDto;
import com.babel.entities.Paiement;
import org.mapstruct.Mapper;

@Mapper
public interface PaiementMapper {
    PaiementDto toPaiement(Paiement paiementEntity);
    Paiement fromPaiement(PaiementDto paiementDto);
}
