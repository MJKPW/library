package com.example.kodillalibrary.domain.reader.dao;

import com.example.kodillalibrary.domain.reader.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ReaderDao extends CrudRepository<Reader, Integer> {

    @Override
    Optional<Reader> findById(Integer id);

    @Override
    List<Reader> findAll();

}
