package com.example.kodillalibrary.control;

import com.example.kodillalibrary.domain.copy.BookCopy;
import com.example.kodillalibrary.domain.copy.BookCopyDto;
import com.example.kodillalibrary.mapping.CopyMapper;
import com.example.kodillalibrary.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library_copies")
public class CopyController {

    private final DbService service;
    private final CopyMapper copyMapper;

    @Autowired
    public CopyController(DbService service, CopyMapper copyMapper) {
        this.service = service;
        this.copyMapper = copyMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies")
    public ResponseEntity<List<BookCopyDto>> getCopies() {
        List<BookCopy> copies = service.getCopies();
        return new ResponseEntity<>(copyMapper.bookCopyListToBookCopyDtoList(copies), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/{copy_id}")
    public ResponseEntity<BookCopyDto> getCopy(@PathVariable("copy_id") int copy_id) {
        BookCopy copy = service.getCopyById(copy_id);
        return new ResponseEntity<>(copyMapper.bookCopyToBookCopyDto(copy), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copy")
    public ResponseEntity<BookCopyDto> getCopyByParam(@RequestParam("copy_id") int copy_id) {
        BookCopy copy = service.getCopyById(copy_id);
        return new ResponseEntity<>(copyMapper.bookCopyToBookCopyDto(copy), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/copy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        service.saveBookCopy(copyMapper.bookCopyDtoToBookCopy(bookCopyDto));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/copy")
    public ResponseEntity<BookCopyDto> updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = service.saveBookCopy(copyMapper.bookCopyDtoToBookCopy(bookCopyDto));
        return ResponseEntity.ok(copyMapper.bookCopyToBookCopyDto(bookCopy));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/change/copy/status")
    public ResponseEntity<BookCopyDto> changeCopyStatus(@RequestParam("copy_id") int copy_id) {
        BookCopy copy = service.changeStatus(copy_id);
        return new ResponseEntity<>(copyMapper.bookCopyToBookCopyDto(copy), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/return/copy/count")
    public ResponseEntity<Integer> getCopyCount(@RequestParam("titleId") int titleId) {
        return new ResponseEntity<>(service.returnCopyCount(titleId), HttpStatus.OK);
    }

}
