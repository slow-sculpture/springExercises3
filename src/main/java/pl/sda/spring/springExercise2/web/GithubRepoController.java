package pl.sda.spring.springExercise2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.spring.springExercise2.service.GithubRepoService;

@Controller
public class GithubRepoController {

    private GithubRepoService githubRepoService;

    @Autowired
    public GithubRepoController(GithubRepoService githubRepoService) {
        this.githubRepoService = githubRepoService;
    }

    //adnotacja sprawiajaca ze mamy GET
    @GetMapping("/getRepo/{user}/{repositoryName}")
    public ResponseEntity<String> getRepositoryByUserAndRepo(@PathVariable("user") String user,
                                                            @PathVariable("repositoryName") String repositoryName) {
        String response = githubRepoService.getRepoByUserAndRepoName(user, repositoryName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
