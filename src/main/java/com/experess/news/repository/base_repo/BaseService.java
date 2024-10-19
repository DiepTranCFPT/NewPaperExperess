package com.experess.news.repository.base_repo;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<T,ID> {


    protected final BaseRepository<T,ID> baseRepository;

    public BaseService(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public T save(T entity) {
        return baseRepository.save(entity);
    }

    public Optional<T> findById(ID id) {
        return baseRepository.findById(id);
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }
}
