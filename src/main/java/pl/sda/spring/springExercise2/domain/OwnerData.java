package pl.sda.spring.springExercise2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OwnerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
   /* @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private AuthorData authorData;
*/
    @Column(unique = true)
    private String login;
    //adnotacja zeby pole moglo sie inaczej nazywac niz dane z githuba (opcjonalne)
    @JsonProperty("site_admin")
    private Boolean siteAdmin;
}
