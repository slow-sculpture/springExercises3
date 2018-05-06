package pl.sda.spring.springExercise2.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SingleCommit {
    private AuthorData author;
    private AuthorData committer;
    private String message;
}
