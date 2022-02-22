package com.example.kodillalibrary.domain;

import com.example.kodillalibrary.domain.borrowed.Borrowed;
import com.example.kodillalibrary.domain.borrowed.dao.BorrowedDao;
import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.dao.BookCopyDao;
import com.example.kodillalibrary.domain.reader.Reader;
import com.example.kodillalibrary.domain.reader.dao.ReaderDao;
import com.example.kodillalibrary.domain.title.Title;
import com.example.kodillalibrary.domain.title.dao.TitleDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class TestSuite {

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private BorrowedDao borrowedDao;

    @Test
    public void createAll() {
        //Given
        Reader firstReader = new Reader (
                 "Jan",
                 "Kowalski");
        Title firstTitle = new Title (
                 "Mały Książę",
                "Antoine’a de Saint-Exupéry’ego",
                          LocalDate.of(1943, 4, 6));

        BookCopy firstCopy = new BookCopy();

        Borrowed borrowed = new Borrowed (
                LocalDate.of(2022, 1, 5),
                LocalDate.of(2022, 2, 11));

        borrowed.setBookCopyId(firstCopy.getId());
        borrowed.setReaderId(firstReader.getId());
        firstCopy.setTitleId(firstTitle.getId());

        //When
        readerDao.save(firstReader);
        firstCopy.setStatus(false);
        bookCopyDao.save(firstCopy);
        borrowedDao.save(borrowed);
        titleDao.save(firstTitle);
        //Then
        Assertions.assertNotEquals(0, firstReader.getId());
        Assertions.assertNotEquals(0, firstCopy.getId());
        Assertions.assertNotEquals(0, firstTitle.getId());
        //CleanUp
        try {
            readerDao.deleteAll();
            titleDao.deleteAll();
            bookCopyDao.deleteAll();
            borrowedDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUniqueTitles() {
        //Given
        Title firstTitle = new Title (
                "Mały Książę",
                "Antoine’a de Saint-Exupéry’ego",
                LocalDate.of(1943, 4, 6));

        BookCopy firstCopy = new BookCopy();
        BookCopy secondCopy = new BookCopy();
        firstCopy.setTitleId(firstTitle.getId());
        secondCopy.setTitleId(firstTitle.getId());
        //When
        titleDao.save(firstTitle);
        int expectedTitleId = firstCopy.getTitleId();
        //Then
        Assertions.assertEquals(expectedTitleId, secondCopy.getTitleId());
        //CleanUp
        try {
            titleDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTables() {
        //Given
        Reader firstReader = new Reader (
                "Jan",
                "Kowalski");
        Title firstTitle = new Title (
                "Mały Książę",
                "Antoine’a de Saint-Exupéry’ego",
                LocalDate.of(1943, 4, 6));

        BookCopy firstCopy = new BookCopy();

        Borrowed borrowed = new Borrowed (
                LocalDate.of(2022, 1, 5),
                LocalDate.of(2022, 2, 11));

        readerDao.save(firstReader);
        titleDao.save((firstTitle));
        bookCopyDao.save((firstCopy));
        borrowedDao.save(borrowed);

    }

    @Test
    public void clear() {
        readerDao.deleteAll();
        titleDao.deleteAll();
        bookCopyDao.deleteAll();
        borrowedDao.deleteAll();
    }

}