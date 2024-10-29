package com.t1.techradar.controller;

import com.t1.techradar.dto.DashBoardResponse;
import com.t1.techradar.dto.PollRequest;
import com.t1.techradar.model.Poll;
import com.t1.techradar.model.Ring;
import com.t1.techradar.model.Technology;
import com.t1.techradar.model.User;
import com.t1.techradar.service.PollService;
import com.t1.techradar.service.RingService;
import com.t1.techradar.service.TechnologyService;
import com.t1.techradar.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.t1.techradar.utils.Utils.BAD_REQUEST;
import static com.t1.techradar.utils.Utils.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class PollController {
    private final TechnologyService technologyService;
    private final UserService userService;
    private final PollService pollService;
    private final RingService ringService;


    @PostMapping("/poll")
    public ResponseEntity<String> poll(@RequestParam Long admin_id,
            @Valid @RequestBody PollRequest pollRequest) {
        System.out.println(pollRequest.getTech_id());
        pollService.save(Poll
                        .builder()
                        .user(userService.findById(admin_id).orElseThrow(() -> BAD_REQUEST))
                        .technology(technologyService.findById(pollRequest.getTech_id()).orElseThrow(() -> NOT_FOUND))
                        .ring(ringService.findByName(pollRequest.getRingResult()).orElseThrow(() -> BAD_REQUEST))
                        .time(LocalDateTime.now())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("Результат опроса успешно добавлен");
    }

    @GetMapping("/api/dashboard/{id}")
    public ResponseEntity<DashBoardResponse> dashBoard(@PathVariable(name = "id") Long id) {
        Technology technology = technologyService.findById(id)
                .orElseThrow(() -> NOT_FOUND);

        List<Poll> pollList = pollService.getVotes(id);
        pollList.sort(Comparator.comparing(Poll::getUser, Comparator.comparing(User::getUserId))
                .thenComparing(Poll::getTime).reversed());

        long userId = -1L;
        Map<String, Integer> votes = ringService.getAll().stream().collect(Collectors.toMap(Ring::getRingName, r -> 0));
        for (Poll poll: pollList) {
            long curId = poll.getUser().getUserId();
            if (curId != userId) {
                votes.merge(poll.getRing().getRingName(), 1, Integer::sum);
            }
            userId = curId;
        }

        return ResponseEntity.ok(DashBoardResponse.builder()
                .category(technology.getCategory().getCatName())
                .section(technology.getSection().getSecName())
                .ring(technology.getRing().getRingName())
                .votes(votes)
                .build());
    }

}
