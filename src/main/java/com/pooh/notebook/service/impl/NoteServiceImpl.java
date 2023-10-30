package com.pooh.notebook.service.impl;

import com.pooh.notebook.Entity.Note;
import com.pooh.notebook.Entity.User;
import com.pooh.notebook.dto.NoteDto;
import com.pooh.notebook.repository.UserRepository;
import com.pooh.notebook.security.JwtTokenProvider;
import com.pooh.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    private JwtTokenProvider tokenProvider;
    private UserRepository userRepository;
    @Autowired
    public NoteServiceImpl(JwtTokenProvider tokenProvider,UserRepository userRepository){
        this.tokenProvider =tokenProvider;
        this.userRepository =userRepository;
    }
    @Override
    public List<NoteDto> addNote(String token) {
        String username = "";
        if(StringUtils.hasText(token)&&tokenProvider.validateToken(token)){
            username = tokenProvider.getUsername(token);
        }
        User user = userRepository.findByUsername(username).orElseThrow();
        Note newNote = new Note();
        newNote.setBody("");
        user.addNote(newNote);
        userRepository.save(user);
        return user.getNotes().stream().map(NoteDto::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getNotes(String token) {
        String username = "";
        if(StringUtils.hasText(token)&&tokenProvider.validateToken(token)){
            username = tokenProvider.getUsername(token);
        }
        User user = userRepository.findByUsername(username).orElseThrow();
        return user.getNotes().stream().map(NoteDto::mapToDto).collect(Collectors.toList());
    }
}
