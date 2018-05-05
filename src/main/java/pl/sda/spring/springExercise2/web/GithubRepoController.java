package pl.sda.spring.springExercise2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.spring.springExercise2.service.GithubRepoService;
import pl.sda.spring.springExercise2.service.domain.CommitData;
import pl.sda.spring.springExercise2.service.domain.GithubData;

import java.util.List;

@Controller
public class GithubRepoController {

    private GithubRepoService githubRepoService;

    @Autowired
    public GithubRepoController(GithubRepoService githubRepoService) {
        this.githubRepoService = githubRepoService;
    }

    //adnotacja sprawiajaca ze mamy GET
    @GetMapping("/getRepo/{user}/{repositoryName}")
    //public ResponseEntity<String> getRepositoryByUserAndRepo(@PathVariable("user") String user,
    public ResponseEntity<GithubData> getRepositoryByUserAndRepo(
            @PathVariable("user") String user,
            @PathVariable("repositoryName") String repositoryName) {
        //String response = githubRepoService.getRepoByUserAndRepoName(user, repositoryName);
        GithubData response = githubRepoService.getRepoByUserAndRepoName(user, repositoryName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getRepo/{user}/{repositoryName}/commits")
    public ResponseEntity<List<CommitData>> getCommitsForRepositoryByUserAndRepo(
            @PathVariable("user") String user,
            @PathVariable("repositoryName") String repositoryName) {
       List<CommitData> response = githubRepoService.getCommitsByUserAndRepoName(user,repositoryName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
