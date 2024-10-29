package com.t1.techradar.repository;

import com.t1.techradar.model.Ring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RingRepository extends JpaRepository<Ring, Long> {

    Optional<Ring> findByRingName(String ring);

}
