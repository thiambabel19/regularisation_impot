package com.babel.mapping;

import com.babel.dto.DeclarationDto;
import com.babel.entities.Declaration;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarationMapper {
    DeclarationDto toDeclaration(Declaration declarationEntity);
    Declaration fromDeclaration(DeclarationDto declarantDto);
}
