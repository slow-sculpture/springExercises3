package pl.sda.spring.springExercise2.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sda.spring.springExercise2.domain.*;
import pl.sda.spring.springExercise2.errorHandling.SDAException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


// TESTY JEDNOSTOKOWE

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitRepoServiceTest {

    private final static String url = "https://api.github.com/repos/{owner}/{repo}";
    @Mock //wez wszystkie publiczne pola i zaimplementuje je tak zeby nic nie robily
    private RestTemplate restTemplate;
    @InjectMocks //wszystkie mocki ww. definiujemy jako parametry konstruktora
    private GithubRepoService githubRepoService;

    @Test
    public void shouldReturnValidResponseForQuery() {
        // given
        OwnerData ownerData = new OwnerData();
        ownerData.setLogin("test_login");
        ownerData.setSiteAdmin(false);

        GithubData githubData = new GithubData();
        githubData.setFull_name("test_name");
        githubData.setOwner(ownerData);
        githubData.setDescription("test_description");

        //////when, any, eq - statyczna metoda mockito
        /////any() jakis
        ////eq() dokladnie ten - jesli wszystkie sa eq to nie musimy pisac wcale
        when(restTemplate.getForObject(any(String.class), eq(GithubData.class),
                eq("userName"), eq("repositoryName"))).thenReturn(githubData);
        // when
        GithubData underTest = githubRepoService.getRepoByUserAndRepoName("userName",
                "repositoryName");
        // then
        assertThat(underTest.getFull_name()).isEqualTo(githubData.getFull_name());
    }

    @Test
    public void shouldGetErrorWhen4xxFromGithub() {
        //given
        String errorMessage = "test_error";

        when(restTemplate.getForObject(url, GithubData.class,
                "testUser", "testRepo"))
                .thenThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN, errorMessage));

        //when
        GithubData underTest = githubRepoService.getRepoByUserAndRepoName("testUser",
                "testRepo");

        //then
        assertThat(underTest.getError()).isEqualTo(HttpStatus.FORBIDDEN.value() + " " + errorMessage);
        //assertThat(underTest.getError()).isEqualTo("");
    }

    @Test
    public void shouldReturnValidResponsneForCommitsQuery() {
        //given
        AuthorData authorData = new AuthorData();
        authorData.setName("test_name");
        authorData.setEmail("test_email");
        authorData.setDate(LocalDateTime.now());

        SingleCommit singleCommit = new SingleCommit();
        singleCommit.setAuthor(authorData);
        singleCommit.setCommitter(authorData);
        singleCommit.setMessage("test_message");

        CommitData commitData = new CommitData();
        commitData.setUrl(url);
        commitData.setCommit(singleCommit);

        CommitData[] commitData1 = {commitData, commitData, commitData};

        List<CommitData> commitDataList = Arrays.asList(commitData1);


        when(restTemplate.getForObject(url + "/commits", CommitData[].class,
                "userName", "repositoryName")).thenReturn(commitData1);

        //when
        List<CommitData> underTest = githubRepoService.getCommitsByUserAndRepoName("userName",
                "repositoryName");

        //then
        assertThat(underTest.subList(0, 3)).isEqualTo(commitDataList.subList(0, 3));
    }

    @Test
    public void shouldReturnValidResponsneForCommitsQuery2() {
        //given
        String url1 = "url_1";
        String url2 = "url_2";
        CommitData[] data = new CommitData[3];
        data[0] = new CommitData();
        data[1] = new CommitData();
        data[1].setUrl("url_1");
        data[2] = new CommitData();
        data[2].setUrl("url_2");
        when(restTemplate.getForObject(url + "/commits", CommitData[].class,
                "testUser", "testRepo")).thenReturn(data);
        //when
        List<CommitData> underTest = githubRepoService.getCommitsByUserAndRepoName("userName",
                "repositoryName");

        //then
        assertThat(underTest.size()).isEqualTo(data.length);
        /*assertThat(underTest
                .stream()  //rozdzielenie wszystkich CommitData
                .map(CommitData::getUrl) //pobranie kazdego CommitData
                .filter(Objects::nonNull) //przefiltorwanie zeby null nie bralo
                .collect(Collectors.toList())  //zebranie wszystkich elementow z powrotem do listy
        ).containsExactlyInAnyOrder(url1,url2);
               */

    }

    @Test (expected = SDAException.class)
    public void shouldGetSDAException() {
        //given
        String errorMessage = "test_error";

        when(restTemplate.getForObject(url + "/commits", CommitData[].class,
                "testUser", "testRepo"))
                .thenThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN, errorMessage));

        //when
        githubRepoService.getCommitsByUserAndRepoName("testUser",
                "testRepo");


    }
}
