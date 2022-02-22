package com.example.kodillalibrary.mapping;

import com.example.kodillalibrary.domain.reader.Reader;
import com.example.kodillalibrary.domain.reader.ReaderDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReaderMapper {

    public ReaderDto readerToReaderDto(Reader reader) {
        return new ReaderDto(reader.getId(),
                             reader.getFirstname(),
                             reader.getLastname(),
                             reader.getDate());
    }

    public Reader readerDtoToReader(ReaderDto readerDto) {
        return new Reader(readerDto.getId(),
                          readerDto.getFirstname(),
                          readerDto.getLastname(),
                          readerDto.getDate());
    }

    public List<ReaderDto> readerListToReadDtoList(List<Reader> readerList) {
        return readerList.stream()
                         .map(this::readerToReaderDto)
                         .collect(Collectors.toList());
    }
}
