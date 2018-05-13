package pl.sda.spring.springExercise2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.springExercise2.domain.CommitData;

import java.util.List;

@Repository
public interface CommitDataRepository extends JpaRepository<CommitData,Long>{

        List<CommitData> getAllByUrlContaining(String fullName);
        boolean existsByUrlContaining(String url);
}
