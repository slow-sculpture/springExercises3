package pl.sda.spring.springExercise2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GithubData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private OwnerData owner;
    private String full_name;
    private String description;
    private String url;
    private String commits_url;
    private Integer watchers_count;
    @JsonIgnore
    private String error;

}
