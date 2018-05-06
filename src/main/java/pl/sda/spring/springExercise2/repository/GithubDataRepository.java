package pl.sda.spring.springExercise2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.springExercise2.domain.GithubData;

@Repository
public interface GithubDataRepository extends JpaRepository<GithubData,Long>{
}
