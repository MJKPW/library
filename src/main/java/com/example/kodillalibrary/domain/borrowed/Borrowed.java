package com.example.kodillalibrary.domain.borrowed;

import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.reader.Reader;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "borrowed")
@AllArgsConstructor
public class Borrowed {

    private int id;
    private int bookCopyId;
    private int readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private long daysOverdue;

    public Borrowed() {}

    public Borrowed(LocalDate borrowDate, LocalDate returnDate) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "reader_id")
    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    @Column(name = "book_copy_id")
    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    @Column(name = "borrow_date")
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Column(name = "return_date")
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Column(name = "days_overdue")
    public long getDaysOverdue() {
        if(LocalDate.now().isAfter(returnDate))
            return ChronoUnit.DAYS.between(returnDate, LocalDate.now());
        else
            return 0;
    }

    public void setDaysOverdue(long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }
}
