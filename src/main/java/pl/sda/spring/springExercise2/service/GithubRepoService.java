package pl.sda.spring.springExercise2.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sda.spring.springExercise2.service.domain.CommitData;
import pl.sda.spring.springExercise2.service.domain.GithubData;
import pl.sda.spring.springExercise2.service.errorHandling.SDAException;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubRepoService {

    private final static String url = "https://api.github.com/repos/{owner}/{repo}";

    private RestTemplate restTemplate;

    @Autowired
    public GithubRepoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //public String getRepoByUserAndRepoName(String userName, String repositoryName) {
    public GithubData getRepoByUserAndRepoName(String userName, String repositoryName) {
        //String response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}", String.class,
       try{
           GithubData response = restTemplate.getForObject(
                url, GithubData.class, userName, repositoryName);
        return response;
       }catch (HttpClientErrorException ex){
           GithubData errorResponse = new GithubData();
           errorResponse.setError(ex. getMessage());
           return errorResponse;
       }
    }

    public List<CommitData> getCommitsByUserAndRepoName(String userName, String repositoryName) {
        try{
            CommitData[] response = restTemplate.getForObject(
                url+"/commits", CommitData[].class, userName, repositoryName);
        List<CommitData> commitDataList = Arrays.asList(response);
        return commitDataList.subList(0,3);
        }catch (HttpClientErrorException ex){
            throw new SDAException(ex.getMessage());
        }
    }
}
