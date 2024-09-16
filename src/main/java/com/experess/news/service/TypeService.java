package com.experess.news.service;

import com.experess.news.entity.Type;
import com.experess.news.iservice.ITypeService;
import com.experess.news.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService implements ITypeService {
    private final ITypeRepository iTypeRepository;

    @Autowired
    public TypeService(ITypeRepository iTypeRepository) {
        this.iTypeRepository = iTypeRepository;
    }

    @Override
    public Type getType(String typeName) {
        return iTypeRepository.findByTypeNameContaining(typeName).orElseThrow(() ->
                new RuntimeException("Could not find type " + typeName));
    }

}
