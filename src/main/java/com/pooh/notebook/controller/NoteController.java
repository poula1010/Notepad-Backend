package com.pooh.notebook.controller;

import com.pooh.notebook.Entity.Note;
import com.pooh.notebook.Entity.User;
import com.pooh.notebook.dto.NoteDto;
import com.pooh.notebook.exception.NoteAPIException;
import com.pooh.notebook.repository.UserRepository;
import com.pooh.notebook.security.JwtTokenProvider;
import com.pooh.notebook.service.AuthService;
import com.pooh.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private NoteService noteService;
    private JwtTokenProvider tokenProvider;
    @Autowired
    public NoteController(NoteService noteService ,JwtTokenProvider jwtTokenProvider){
        this.noteService = noteService;
        this.tokenProvider = jwtTokenProvider;
    }
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<NoteDto>> addNote(@RequestHeader Map<String, String> headers){
        String token;
        try{
            token = headers.get("authorizationBearer ");
            List<NoteDto> notes = noteService.addNote(token);
            return new ResponseEntity<>(notes,HttpStatus.OK);
        }
        catch (Exception e){
            throw new NoteAPIException(HttpStatus.BAD_REQUEST,"invalid User");
        }


    }
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<NoteDto>>  getNotes(@RequestHeader Map<String, String> headers){

        String token;
        try{
            token = headers.get("authorization");
            token = token.split(" ")[1];
            List<NoteDto> notes = noteService.getNotes(token);
            return new ResponseEntity<>(notes,HttpStatus.OK);
        }
        catch (Exception e){
            throw new NoteAPIException(HttpStatus.BAD_REQUEST,"invalid User");
        }

    }

}
