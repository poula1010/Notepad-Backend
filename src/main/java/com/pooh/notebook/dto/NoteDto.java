package com.pooh.notebook.dto;

import com.pooh.notebook.Entity.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private int id;
    private String body;

    public static NoteDto mapToDto(Note note){
        return new NoteDto(note.getId(), note.getBody());
    }
}
