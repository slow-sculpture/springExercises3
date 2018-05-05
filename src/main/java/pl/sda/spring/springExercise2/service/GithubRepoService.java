package pl.sda.spring.springExercise2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubRepoService {
    public String getRepoByUserAndRepoName(String userName, String repositoryName) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}", String.class,
                userName, repositoryName);
        return response;
    }
}
