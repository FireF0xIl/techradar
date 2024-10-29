package com.t1.techradar.service;

import com.t1.techradar.model.Poll;
import com.t1.techradar.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService {
    private final PollRepository pollRepository;

    public List<Poll> getVotes(Long id) {
        return pollRepository.findByTechnologyTechnologyId(id);
    }

    public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }
}
