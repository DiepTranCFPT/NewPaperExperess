package com.example.demo.service;

import com.example.demo.entity.Type;
import com.example.demo.iservice.ITypeService;
import com.example.demo.repository.ITypeRepository;
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
