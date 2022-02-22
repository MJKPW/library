package com.example.kodillalibrary.control;

import com.example.kodillalibrary.domain.borrowed.Borrowed;
import com.example.kodillalibrary.domain.borrowed.BorrowedDto;
import com.example.kodillalibrary.mapping.BorrowedMapper;
import com.example.kodillalibrary.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library_borrowed")
public class BorrowedController {

    private final DbService service;
    private final BorrowedMapper borrowedMapper;

    @Autowired
    public BorrowedController(DbService service, BorrowedMapper borrowedMapper) {
        this.service = service;
        this.borrowedMapper = borrowedMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/borrowed_list")
    public ResponseEntity<List<BorrowedDto>> getBorrowedCopies() {
        List<Borrowed> copies = service.getBurrowedCopies();
        return new ResponseEntity<>(borrowedMapper.borrowedListToBorrowedDtoList(copies), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/borrowed/{copy_id}")
    public ResponseEntity<BorrowedDto> getBorrowedCopy(@PathVariable("copy_id") int copy_id) {
        Borrowed copy = service.getBorrowedById(copy_id);
        return new ResponseEntity<>(borrowedMapper.borrowedToBorrowedDto(copy), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/borrow")
    public ResponseEntity<BorrowedDto> getBorrowedCopyByParam(@RequestParam("copy_id") int copy_id) {
        Borrowed copy = service.getBorrowedById(copy_id);
        return new ResponseEntity<>(borrowedMapper.borrowedToBorrowedDto(copy), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/borrow/book")
    public ResponseEntity<BorrowedDto> borrowCopy (
            @RequestParam("readerId")int readerId,
            @RequestParam("titleId")int titleId,
            @RequestParam("months")int months)
    {
        Borrowed borrowed = service.borrow(readerId, titleId, months);
        return ResponseEntity.ok(borrowedMapper.borrowedToBorrowedDto(borrowed));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/return/book")
    public ResponseEntity<BorrowedDto> returnBook(@RequestParam("borrowedId") int borrowedId) {
        Borrowed copy = service.returnCopy(borrowedId);
        return new ResponseEntity<>(borrowedMapper.borrowedToBorrowedDto(copy), HttpStatus.OK);
    }

}
