package com.babel.mapping;

import com.babel.dto.DeclarantDto;
import com.babel.entities.Declarant;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarantMapper {
    DeclarantDto toDeclarant(Declarant declarantEntity);
    Declarant fromDeclarant(DeclarantDto declarantDto);
}
