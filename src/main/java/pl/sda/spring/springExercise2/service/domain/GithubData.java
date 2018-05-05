package pl.sda.spring.springExercise2.service.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GithubData {
    private OwnerData owner;
    private String full_name;
    private String description;
    private String url;
    private String commits_url;
    private Integer watchers_count;
}
