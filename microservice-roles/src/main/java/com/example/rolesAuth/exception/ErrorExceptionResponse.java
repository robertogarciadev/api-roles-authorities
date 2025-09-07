package com.example.rolesAuth.exception;

import java.time.LocalDateTime;

public record ErrorExceptionResponse(
        int status,
        String message,
        LocalDateTime timestamp) {}
