package pl.sda.spring.springExercise2.service.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class AuthorData {
    private String name;
    private String email;
    private LocalDateTime date;
}
