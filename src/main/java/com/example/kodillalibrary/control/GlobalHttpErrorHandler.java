package com.example.kodillalibrary.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReaderIdNotFoundException.class)
    public ResponseEntity<Object> handleIdNotFoundException(ReaderIdNotFoundException e) {
        return new ResponseEntity<>("Reader with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowedCopyIdNotFoundException.class)
    public ResponseEntity<Object> handleBorrowedCopyIdNotFoundException(BorrowedCopyIdNotFoundException e) {
        return new ResponseEntity<>("Borrowed copy with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CopyIdNotFoundException.class)
    public ResponseEntity<Object> handleCopyIdNotFoundException(CopyIdNotFoundException e) {
        return new ResponseEntity<>("Copy with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleIdNotFoundException.class)
    public ResponseEntity<Object> handleTitleIdNotFoundException(TitleIdNotFoundException e) {
        return new ResponseEntity<>("Title with given id not found exception", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCopiesAvailableException.class)
    public ResponseEntity<Object> handleNoCopiesAvailableException(NoCopiesAvailableException e) {
        return new ResponseEntity<>("No available copies of the given title id", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowedTimeException.class)
    public ResponseEntity<Object> handleBorrowedTimeException(BorrowedTimeException e) {
        return new ResponseEntity<>("Given wrong number of months", HttpStatus.BAD_REQUEST);
    }

}
