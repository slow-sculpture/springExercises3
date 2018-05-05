package pl.sda.spring.springExercise2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.spring.springExercise2.service.domain.CommitData;
import pl.sda.spring.springExercise2.service.domain.GithubData;

@Service
public class GithubRepoService {
    //public String getRepoByUserAndRepoName(String userName, String repositoryName) {
    public GithubData getRepoByUserAndRepoName(String userName, String repositoryName) {
        RestTemplate restTemplate = new RestTemplate();
        //String response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}", String.class,
        GithubData response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}",
                GithubData.class, userName, repositoryName);
        return response;
    }

    public CommitData[] getCommitsByUserAndRepoName(String userName, String repositoryName) {
        RestTemplate restTemplate = new RestTemplate();
        CommitData[] response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}",
                CommitData[].class, userName, repositoryName);
        return response;
    }
}
