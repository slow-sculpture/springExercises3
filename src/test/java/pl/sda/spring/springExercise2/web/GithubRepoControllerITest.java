package pl.sda.spring.springExercise2.web;



//  TEST INTEGRACYJNY

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringExercise2Application.class)
@AutoConfigureMockMvc
public class GithubRepoControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnValidResponse() throws Exception{
        //perform - jakie chcemy miec zapytanie
        // wez (get), wypisz na konsole(print) i parametry spodziewanego wyniku (andExpect)
        mockMvc.perform(get("/getRepo/{user}/{repositoryName}",
                "slow-sculpture", "java-db")).andDo(print())
                .andExpect(status().isOk());
    }
}
