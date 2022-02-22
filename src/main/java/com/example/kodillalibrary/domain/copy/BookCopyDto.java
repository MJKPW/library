package com.example.kodillalibrary.domain.copy;

import com.example.kodillalibrary.domain.title.Title;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCopyDto {
    private int id;
    private int titleId;
    private boolean status;
}
