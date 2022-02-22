package com.example.kodillalibrary.mapping;

import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.copy.BookCopyDto;

import java.util.List;
import java.util.stream.Collectors;

public class CopyMapper {

    public BookCopyDto bookCopyToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(bookCopy.getId(),
                               bookCopy.getTitleId(),
                               bookCopy.isStatus());
    }

    public BookCopy bookCopyDtoToBookCopy(BookCopyDto bookCopyDto) {
        return new BookCopy(bookCopyDto.getId(),
                            bookCopyDto.getTitleId(),
                            bookCopyDto.isStatus());
    }

    public List<BookCopyDto> bookCopyListToBookCopyDtoList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                           .map(this::bookCopyToBookCopyDto)
                           .collect(Collectors.toList());
    }
}
