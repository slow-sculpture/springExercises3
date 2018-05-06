package pl.sda.spring.springExercise2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.springExercise2.domain.AuthorData;

@Repository
public interface AuthorDataRepository extends JpaRepository<AuthorData,Long>{
}
