package com.t1.techradar.repository;

import com.t1.techradar.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findByTechnologyTechnologyId(Long id);
}
