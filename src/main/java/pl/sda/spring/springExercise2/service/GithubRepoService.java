package pl.sda.spring.springExercise2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sda.spring.springExercise2.domain.CommitData;
import pl.sda.spring.springExercise2.domain.GithubData;
import pl.sda.spring.springExercise2.errorHandling.SDAException;
import pl.sda.spring.springExercise2.repository.CommitDataRepository;
import pl.sda.spring.springExercise2.repository.GithubDataRepository;
import pl.sda.spring.springExercise2.repository.OwnerDataRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubRepoService {

    private final static String url = "https://api.github.com/repos/{owner}/{repo}";

    private RestTemplate restTemplate;
    private GithubDataRepository githubDataRepository;
    private CommitDataRepository commitDataRepository;
    private OwnerDataRepository ownerDataRepository;

    @Autowired
    public GithubRepoService(RestTemplate restTemplate,
                             GithubDataRepository githubDataRepository,
                             CommitDataRepository commitDataRepository,
                             OwnerDataRepository ownerDataRepository) {
        this.restTemplate = restTemplate;
        this.githubDataRepository = githubDataRepository;
        this.commitDataRepository = commitDataRepository;
        this.ownerDataRepository = ownerDataRepository;
    }

    @Transactional //zapisz wszystko co jest pod spodem
    //public String getRepoByUserAndRepoName(String userName, String repositoryName) {
    public GithubData getRepoByUserAndRepoName(String userName, String repositoryName) {
        //String response = restTemplate.getForObject("https://api.github.com/repos/{owner}/{repo}", String.class,
        try {

            String fullName = String.format("%s/%s", userName, repositoryName);
            if (githubDataRepository.existsByFullName(fullName)) {
                return githubDataRepository.getByFullName(fullName);
            } else {
                GithubData response = restTemplate.getForObject(
                        url, GithubData.class, userName, repositoryName);
                if(ownerDataRepository.existsByLogin(response.getOwner().getLogin())){
                    response.setOwner(ownerDataRepository.getByLogin(response.getOwner().getLogin()));
                }
                //zapisanie do bazy danych
                githubDataRepository.save(response);
                return response;
            }


        } catch (HttpClientErrorException ex) {
            GithubData errorResponse = new GithubData();
            errorResponse.setError(ex.getMessage());
            return errorResponse;
        }
    }

    public List<CommitData> getCommitsByUserAndRepoName(String userName, String repositoryName) {
        try {
            String fullName = String.format("%s/%s", userName, repositoryName);
            if(commitDataRepository.existsByUrlContaining(fullName))
            {return commitDataRepository.getAllByUrlContaining(fullName);
            }else {
                CommitData[] response = restTemplate.getForObject(
                        url + "/commits", CommitData[].class, userName, repositoryName);
                List<CommitData> commitDataList = Arrays.asList(response);
                commitDataList = commitDataList.size() > 3 ? commitDataList.subList(0, 3) : commitDataList;
                commitDataRepository.saveAll(commitDataList);
                return commitDataList;
            }
        } catch (HttpClientErrorException ex) {
            throw new SDAException(ex.getMessage());
        }
    }
    private static String getFullName(String userName, String repoName){
        return String.format("%s/%s",userName,repoName);
    }
}
