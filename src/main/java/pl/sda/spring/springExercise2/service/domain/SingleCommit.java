package pl.sda.spring.springExercise2.service.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SingleCommit {
    private AuthorData author;
    private AuthorData committer;
    private String message;
}
