package pl.sda.spring.springExercise2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.springExercise2.domain.OwnerData;

@Repository
public interface OwnerDataRepository extends JpaRepository<OwnerData, Long>{
    boolean existsByLogin(String login);

    OwnerData getByLogin(String login);
}
