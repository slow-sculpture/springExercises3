package pl.sda.spring.springExercise2.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.spring.springExercise2.domain.AuthorData;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorDataRepositoryTest {
    @Autowired
    private AuthorDataRepository authorDataRepository;

    @Test
    public void shouldAddDataToRepository(){
        //given
        AuthorData authorData= new AuthorData();
        //authorData.setDate(LocalDateTime.now());
        authorData.setDate(LocalDateTime.now());
        authorData.setEmail("test@test.pl");
        authorData.setName("test");
        //when
        authorDataRepository.save(authorData);
        //then
        List<AuthorData> underTest = authorDataRepository.findAll();
        AuthorData author = underTest.get(0);
        assertThat(underTest.size()).isEqualTo(1);
        assertThat(author.getDate()).isEqualTo(authorData.getDate());
        assertThat(author.getId()).isNotNull();
    }
}
