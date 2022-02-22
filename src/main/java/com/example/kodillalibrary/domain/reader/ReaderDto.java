package com.example.kodillalibrary.domain.reader;

import com.example.kodillalibrary.domain.borrowed.Borrowed;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ReaderDto {
    private int id;
    private final String firstname;
    private final String lastname;
    private final LocalDate date;
}
