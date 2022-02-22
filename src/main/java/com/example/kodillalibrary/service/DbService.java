package com.example.kodillalibrary.service;

import com.example.kodillalibrary.control.*;
import com.example.kodillalibrary.domain.borrowed.Borrowed;
import com.example.kodillalibrary.domain.borrowed.dao.BorrowedDao;
import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.dao.BookCopyDao;
import com.example.kodillalibrary.domain.reader.Reader;
import com.example.kodillalibrary.domain.reader.dao.ReaderDao;
import com.example.kodillalibrary.domain.title.Title;
import com.example.kodillalibrary.domain.title.dao.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class DbService {

    @Autowired
    private BorrowedDao borrowedDao;
    @Autowired
    private BookCopyDao bookCopyDao;
    @Autowired
    private ReaderDao readerDao;
    @Autowired
    private TitleDao titleDao;

    public Reader saveReader(Reader reader) {
        reader.setDate(LocalDate.now());
        return readerDao.save(reader);
    }

    public Title saveTitle(Title title) {
        return titleDao.save(title);
    }

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        if(titleDao.findById(bookCopy.getTitleId()).isPresent()) {
            return bookCopyDao.save(bookCopy);
        } else {
            throw new TitleIdNotFoundException();
        }
    }

    public List<Reader> getReaders() {
        return readerDao.findAll();
    }

    public List<Title> getTitles() {
        return titleDao.findAll();
    }

    public List<BookCopy> getCopies() {
        return bookCopyDao.findAll();
    }

    public List<Borrowed> getBurrowedCopies() {
        return borrowedDao.findAll();
    }

    public Reader getReaderById(int id) {
        return readerDao.findById(id).orElseThrow(ReaderIdNotFoundException::new);
    }

    public Title getTitleById(int id) {
        return titleDao.findById(id).orElseThrow(TitleIdNotFoundException::new);
    }

    public BookCopy getCopyById(int id) {
        return bookCopyDao.findById(id).orElseThrow(CopyIdNotFoundException::new);
    }

    public Borrowed getBorrowedById(int id) {
        return borrowedDao.findById(id).orElseThrow(BorrowedCopyIdNotFoundException::new);
    }

    public Borrowed borrow(int readerId, int titleId, int months) {
        if(months != 1 && months != 2 && months != 3)
            throw new BorrowedTimeException();
        List<BookCopy> copies = getCopies();
        for(int i = 0; i != copies.size(); ++i) {
            if(titleId == copies.get(i).getTitleId() && copies.get(i).isStatus()) {
                BookCopy copy = copies.get(i);
                copy.setStatus(false);
                saveBookCopy(copy);
                Borrowed borrowed = new Borrowed(LocalDate.now(), LocalDate.now().plusMonths(months));
                borrowed.setReaderId(readerId);
                borrowed.setBookCopyId(copy.getId());
                return borrowedDao.save(borrowed);
            }
        }
        throw new NoCopiesAvailableException();
    }

    public BookCopy changeStatus(int bookCopyId) {
        BookCopy copy = getCopyById(bookCopyId);
        copy.setStatus(!copy.isStatus());
        return saveBookCopy(copy);
    }

    public Integer returnCopyCount(int titleId) {
        int count = 0;
        List<BookCopy> copies = getCopies();
        for(var copy : copies) {
            if(copy.getTitleId() == titleId)
                count++;
        }
        return count;
    }

    public Borrowed returnCopy(int borrowedId) {
        Borrowed borrowed = getBorrowedById(borrowedId);
        BookCopy copy = getCopyById(borrowed.getBookCopyId());
        copy.setStatus(true);
        bookCopyDao.save(copy);
        return borrowed;
    }

}
