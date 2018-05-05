package pl.sda.spring.springExercise2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.spring.springExercise2.service.domain.CommitData;
import pl.sda.spring.springExercise2.service.domain.GithubData;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubRepoService {

    private final static String url = "https://api.github.com/repos/{owner}/{repo}";

    private RestTemplate restTemplate;

    public GithubRepoService() {
        this.restTemplate = new RestTemplate();
    }

    //public String getRepoByUserAndRepoName(String userName, String repositoryName) {
    public GithubData getRepoByUserAndRepoName(String userName, String repositoryName) {
        //String response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}", String.class,
        GithubData response = restTemplate.getForObject(
                url, GithubData.class, userName, repositoryName);
        return response;
    }

    public List<CommitData> getCommitsByUserAndRepoName(String userName, String repositoryName) {
        CommitData[] response = restTemplate.getForObject(
                url+"/commits", CommitData[].class, userName, repositoryName);
        List<CommitData> commitDataList = Arrays.asList(response);
        return commitDataList.subList(0,3);
    }
}
