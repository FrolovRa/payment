package com.crud.payment.service;

import com.crud.payment.domain.Iban;
import com.crud.payment.dto.iban.IbanCreateDto;
import com.crud.payment.dto.iban.IbanReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import com.crud.payment.repository.IbanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IbanService {

    private final IbanRepository ibanRepository;
    private final ObjectMapperService mapperService;

    public IbanService(IbanRepository ibanRepository,
                       ObjectMapperService mapperService) {
        this.ibanRepository = ibanRepository;
        this.mapperService = mapperService;
    }

    @Transactional(readOnly = true)
    public IbanReadDto findById(Long id) {
        return ibanRepository
                .findById(id)
                .map(mapperService::toDto)
                .orElseThrow(() -> new EntityNotFoundException(Iban.class, id));
    }

    @Transactional
    public IbanReadDto save(IbanCreateDto ibanDto) {
        Iban entity = mapperService.toEntity(ibanDto);
        entity = ibanRepository.save(entity);
        return mapperService.toDto(entity);
    }
}
