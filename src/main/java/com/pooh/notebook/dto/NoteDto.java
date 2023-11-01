package com.pooh.notebook.dto;

import com.pooh.notebook.Entity.Note;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoteDto {
    private int id;
    private String body;

    public static NoteDto mapToDto(Note note){
        return new NoteDto(note.getId(), note.getBody());
    }

}
