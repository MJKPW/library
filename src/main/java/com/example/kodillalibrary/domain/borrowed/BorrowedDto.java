package com.example.kodillalibrary.domain.borrowed;

import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BorrowedDto {
    private int id;
    private int bookCopyId;
    private int readerId;
    private final LocalDate borrowDate;
    private final LocalDate returnDate;
    private long daysOverdue;
}
