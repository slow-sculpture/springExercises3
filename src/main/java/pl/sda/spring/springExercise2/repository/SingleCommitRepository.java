package pl.sda.spring.springExercise2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.springExercise2.domain.SingleCommit;

@Repository
public interface SingleCommitRepository extends JpaRepository<SingleCommit, Long>{
}
