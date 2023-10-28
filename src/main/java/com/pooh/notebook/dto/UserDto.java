package com.pooh.notebook.dto;

import com.pooh.notebook.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private List<NoteDto> noteDtos;

    public static UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(userDto.getEmail());
        List<NoteDto> noteDtoList = user.getNotes().stream().map(NoteDto::mapToDto).collect(Collectors.toList());
        userDto.setNoteDtos(noteDtoList);
        return userDto;
    }
}
