package com.example.kodillalibrary.control;

import com.example.kodillalibrary.domain.title.Title;
import com.example.kodillalibrary.domain.title.TitleDto;
import com.example.kodillalibrary.mapping.TitleMapper;
import com.example.kodillalibrary.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library_titles")
public class TitleController {

    private final DbService service;
    private final TitleMapper titleMapper;

    @Autowired
    public TitleController(DbService service, TitleMapper titleMapper) {
        this.service = service;
        this.titleMapper = titleMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles")
    public ResponseEntity<List<TitleDto>> getTitles() {
        List<Title> titles = service.getTitles();
        return new ResponseEntity<>(titleMapper.titleListToTitleDtoList(titles), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles/{title_id}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable("title_id") int title_id) {
        Title title = service.getTitleById(title_id);
        return new ResponseEntity<>(titleMapper.titleToTitleDto(title), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/title")
    public ResponseEntity<TitleDto> getTitleByParam(@RequestParam("title_id") int title_id) {
        Title title = service.getTitleById(title_id);
        return new ResponseEntity<>(titleMapper.titleToTitleDto(title), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/title", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTitle(@RequestBody TitleDto titleDto) {
        service.saveTitle(titleMapper.titleDtoToTitle(titleDto));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/title")
    public ResponseEntity<TitleDto> updateTitle(@RequestBody TitleDto titleDto) {
        Title title = service.saveTitle(titleMapper.titleDtoToTitle(titleDto));
        return ResponseEntity.ok(titleMapper.titleToTitleDto(title));
    }

}
