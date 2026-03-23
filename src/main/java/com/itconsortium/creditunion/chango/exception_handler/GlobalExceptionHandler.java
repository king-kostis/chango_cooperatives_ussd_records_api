package com.itconsortium.creditunion.chango.exception_handler;

import com.itconsortium.creditunion.chango.exceptions.EmailSendingException;
import com.itconsortium.creditunion.chango.exceptions.NoTransactionsAvailableException;
import com.itconsortium.creditunion.chango.exceptions.RecurringDebitsNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecurringDebitsNotFoundException.class)
    public ResponseEntity<String> handleSubscriptionNotFound(RecurringDebitsNotFoundException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<String> handleEmailSendingException(){
        return ResponseEntity.status(500).body("Problem sending email");
    }

    @ExceptionHandler(NoTransactionsAvailableException.class)
    public ResponseEntity<String> handleNoAvailableStatementException(NoTransactionsAvailableException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e){
        return ResponseEntity.status(500).body("Something went wrong in the server");
    }
}
