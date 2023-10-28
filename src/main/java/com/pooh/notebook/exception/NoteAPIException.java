package com.pooh.notebook.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class NoteAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
