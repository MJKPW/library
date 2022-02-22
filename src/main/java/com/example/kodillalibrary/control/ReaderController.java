package com.example.kodillalibrary.control;

import com.example.kodillalibrary.domain.reader.Reader;
import com.example.kodillalibrary.domain.reader.ReaderDto;
import com.example.kodillalibrary.mapping.ReaderMapper;
import com.example.kodillalibrary.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library_readers")
public class ReaderController {

    private final DbService service;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderController(DbService service, ReaderMapper readerMapper) {
        this.service = service;
        this.readerMapper = readerMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readers")
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers = service.getReaders();
        return new ResponseEntity<>(readerMapper.readerListToReadDtoList(readers), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readers/{reader_id}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable("reader_id") int reader_id) {
        Reader reader = service.getReaderById(reader_id);
        return new ResponseEntity<>(readerMapper.readerToReaderDto(reader), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reader")
    public ResponseEntity<ReaderDto> getReaderByParam(@RequestParam("reader_id") int reader_id){
        Reader reader = service.getReaderById(reader_id);
        return new ResponseEntity<>(readerMapper.readerToReaderDto(reader), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/reader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody ReaderDto readerDto) {
        service.saveReader(readerMapper.readerDtoToReader(readerDto));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/reader")
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = service.saveReader(readerMapper.readerDtoToReader(readerDto));
        return ResponseEntity.ok(readerMapper.readerToReaderDto(reader));
    }

}
