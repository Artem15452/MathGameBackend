package com.math.game.Repository;

import com.math.game.Entity.UserResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResultRepository extends JpaRepository<UserResult, Long> {
    List<UserResult> findTop10ByOrderByCorrectAnswerDesc();
}
