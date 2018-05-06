package pl.sda.spring.springExercise2.web;



//  TEST INTEGRACYJNY
// MockMVC - model (katalog domain) view (service) controller
// w sygnaturze ITest

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.spring.springExercise2.SpringExercise2Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringExercise2Application.class)
@AutoConfigureMockMvc
public class GithubRepoControllerITest {
    private static final String URL = "/getRepo/{user}/{repositoryName}";
    private static final String USER = "slow-sculpture";
    private static final String REPO = "java-db";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnValidResponse() throws Exception{
        //perform - jakie chcemy miec zapytanie
        // wez (get), wypisz na konsole(print) i parametry spodziewanego wyniku (andExpect)
        mockMvc.perform(get(URL, USER, REPO)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner.login").value(USER));
    }
    @Test
    public void shoulThrowException4xx() throws Exception{
        //perform - jakie chcemy miec zapytanie
        // wez (get), wypisz na konsole(print) i parametry spodziewanego wyniku (andExpect)
        mockMvc.perform(get(URL, "test_user", "test_repo")).andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void shouldReturnCommitsByUserAndRepo() throws Exception{
        mockMvc.perform(get(URL+"/commits",USER,REPO))
        .andDo(print())
        .andExpect(status().isOk());
    }
}
