package com.example.kodillalibrary.domain.title;

import com.example.kodillalibrary.domain.copy.BookCopy;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class TitleDto {
    private int id;
    private final String title;
    private final String author;
    private final LocalDate releaseDate;
}
