package ru.faickoff.api.auth.dto.response.error;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseErrorResponse {
    
    private LocalDateTime timestamp;

    private int status;

    private String error;

    private Map<String, String> details;

    private String path;
}
