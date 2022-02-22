package com.example.kodillalibrary.domain.dao;

import com.example.kodillalibrary.domain.copy.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopyDao extends CrudRepository<BookCopy, Integer> {

    @Override
    Optional<BookCopy> findById(Integer id);

    @Override
    List<BookCopy> findAll();

}
