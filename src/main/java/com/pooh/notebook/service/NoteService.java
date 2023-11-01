package com.pooh.notebook.service;

import com.pooh.notebook.dto.NoteDto;

import java.util.List;

public interface NoteService {
    public NoteDto addNote(String token);
    public List<NoteDto> getNotes(String token);

    public void editNote(String token,NoteDto noteDto);
}
