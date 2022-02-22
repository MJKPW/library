package com.example.kodillalibrary.mapping;

import com.example.kodillalibrary.domain.borrowed.Borrowed;
import com.example.kodillalibrary.domain.borrowed.BorrowedDto;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowedMapper {

    public BorrowedDto borrowedToBorrowedDto(Borrowed borrowed) {
        return new BorrowedDto(borrowed.getId(),
                               borrowed.getBookCopyId(),
                               borrowed.getReaderId(),
                               borrowed.getBorrowDate(),
                               borrowed.getReturnDate(),
                               borrowed.getDaysOverdue());
    }

    public Borrowed borrowedDtoToBurrowed(BorrowedDto borrowed) {
        return new Borrowed(borrowed.getId(),
                            borrowed.getBookCopyId(),
                            borrowed.getReaderId(),
                            borrowed.getBorrowDate(),
                            borrowed.getReturnDate(),
                            borrowed.getDaysOverdue());
    }

    public List<BorrowedDto> borrowedListToBorrowedDtoList(List<Borrowed> borrowedList) {
        return borrowedList.stream()
                           .map(this::borrowedToBorrowedDto)
                           .collect(Collectors.toList());
    }
}
