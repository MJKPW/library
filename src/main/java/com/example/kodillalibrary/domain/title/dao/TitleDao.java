package com.example.kodillalibrary.domain.title.dao;

import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.title.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title, Integer> {

    @Override
    Optional<Title> findById(Integer id);

    @Override
    List<Title> findAll();
}
