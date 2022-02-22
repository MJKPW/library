package com.example.kodillalibrary.mapping;

import com.example.kodillalibrary.domain.title.Title;
import com.example.kodillalibrary.domain.title.TitleDto;

import java.util.List;
import java.util.stream.Collectors;

public class TitleMapper {

    public TitleDto titleToTitleDto(Title title) {
        return new TitleDto(title.getId(),
                            title.getTitle(),
                            title.getAuthor(),
                            title.getReleaseDate());
    }

    public Title titleDtoToTitle(TitleDto titleDto) {
        return new Title(titleDto.getId(),
                         titleDto.getTitle(),
                         titleDto.getAuthor(),
                         titleDto.getReleaseDate());
    }

    public List<TitleDto> titleListToTitleDtoList(List<Title> titleList) {
        return titleList.stream()
                        .map(this::titleToTitleDto)
                        .collect(Collectors.toList());
    }

}
