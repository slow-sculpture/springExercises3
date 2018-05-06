package pl.sda.spring.springExercise2.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommitData {
    private String url;
    private SingleCommit commit;
}
