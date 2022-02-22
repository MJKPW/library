package com.example.kodillalibrary.domain.borrowed.dao;

import com.example.kodillalibrary.domain.borrowed.Borrowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowedDao extends CrudRepository<Borrowed, Integer> {

    @Override
    List<Borrowed> findAll();

    @Override
    Optional<Borrowed> findById(Integer id);
}