package pl.sda.spring.springExercise2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sda.spring.springExercise2.domain.CommitData;
import pl.sda.spring.springExercise2.domain.GithubData;
import pl.sda.spring.springExercise2.errorHandling.SDAException;
import pl.sda.spring.springExercise2.repository.GithubDataRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubRepoService {

    private final static String url = "https://api.github.com/repos/{owner}/{repo}";

    private RestTemplate restTemplate;
    private GithubDataRepository githubDataRepository;

    @Autowired
    public GithubRepoService(RestTemplate restTemplate, GithubDataRepository githubDataRepository) {
        this.restTemplate = restTemplate;
        this.githubDataRepository = githubDataRepository;
    }

    @Transactional //zapisz wszystko co jest pod spodem
    //public String getRepoByUserAndRepoName(String userName, String repositoryName) {
    public GithubData getRepoByUserAndRepoName(String userName, String repositoryName) {
        //String response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}", String.class,
        try {
            GithubData response;
            String fullName = String.format("%s/%s", userName, repositoryName);
            if (githubDataRepository.existsByFullName(fullName)) {
                response = githubDataRepository.getByFullName(fullName);
            } else {
                response = restTemplate.getForObject(
                        url, GithubData.class, userName, repositoryName);
                //zapisanie do bazy danych
                githubDataRepository.save(response);
            }

            return response;
        } catch (HttpClientErrorException ex) {
            GithubData errorResponse = new GithubData();
            errorResponse.setError(ex.getMessage());
            return errorResponse;
        }
    }

    public List<CommitData> getCommitsByUserAndRepoName(String userName, String repositoryName) {
        try {
            CommitData[] response = restTemplate.getForObject(
                    url + "/commits", CommitData[].class, userName, repositoryName);
            List<CommitData> commitDataList = Arrays.asList(response);
            return commitDataList.size() > 3 ? commitDataList.subList(0, 3) : commitDataList;
        } catch (HttpClientErrorException ex) {
            throw new SDAException(ex.getMessage());
        }
    }
}
