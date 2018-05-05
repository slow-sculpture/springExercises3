package pl.sda.spring.springExercise2.service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OwnerData {
    private String login;
    //adnotacja zeby pole moglo sie inaczej nazywac niz dane z githuba (opcjonalne)
    @JsonProperty("site_admin")
    private Boolean siteAdmin;
}
