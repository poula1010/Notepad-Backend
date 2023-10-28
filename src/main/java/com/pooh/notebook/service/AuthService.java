package com.pooh.notebook.service;

import com.pooh.notebook.dto.LoginDto;
import com.pooh.notebook.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
