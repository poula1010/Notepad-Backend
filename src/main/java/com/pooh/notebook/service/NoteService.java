package com.pooh.notebook.service;

import com.pooh.notebook.dto.NoteDto;

import java.util.List;

public interface NoteService {
    public List<NoteDto> addNote(String token);
    public List<NoteDto> getNotes(String token);
}
