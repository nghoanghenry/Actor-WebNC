package com.hoang.actorservice.controller;

import com.hoang.actorservice.dto.ActorRequestDTO;
import com.hoang.actorservice.dto.ActorResponseDTO;
import com.hoang.actorservice.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("actors")
@Tag(name = "Actors", description = "API for managing actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    // 🟢 GET ALL
    @GetMapping
    @Operation(summary = "Get all actors")
    public ResponseEntity<List<ActorResponseDTO>> getAllActors() {
        long start = System.currentTimeMillis();
        MDC.put("endpoint", "GET /actors");
        MDC.put("method", "getAllActors");
        MDC.put("requestId", UUID.randomUUID().toString());

        log.info("Received request to get all actors");

        List<ActorResponseDTO> actors = actorService.getAllActor();
        long duration = System.currentTimeMillis() - start;

        log.info("Returned {} actors in {} ms", actors.size(), duration);

        MDC.clear();
        return ResponseEntity.ok(actors);
    }

    // 🟢 GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Get actor by id")
    public ResponseEntity<ActorResponseDTO> getActorById(@PathVariable Integer id) {
        long start = System.currentTimeMillis();
        MDC.put("endpoint", "GET /actors/{id}");
        MDC.put("method", "getActorById");
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("actorId", String.valueOf(id));

        log.info("Received request to get actor with id={}", id);

        ActorResponseDTO actor = actorService.getActorById(id);
        long duration = System.currentTimeMillis() - start;

        log.info("Returned actor id={} in {} ms", id, duration);

        MDC.clear();
        return ResponseEntity.ok(actor);
    }

    // 🟢 CREATE
    @PostMapping
    @Operation(summary = "Create actor")
    public ResponseEntity<ActorResponseDTO> addActor(@RequestBody ActorRequestDTO actorRequestDTO) {
        long start = System.currentTimeMillis();
        MDC.put("endpoint", "POST /actors");
        MDC.put("method", "addActor");
        MDC.put("requestId", UUID.randomUUID().toString());

        log.info("Received request to create actor with firstName='{}' lastName='{}'",
                actorRequestDTO.getFirst_name(), actorRequestDTO.getLast_name());

        ActorResponseDTO createdActor = actorService.createActor(actorRequestDTO);
        long duration = System.currentTimeMillis() - start;

        log.info("Created actor id={} in {} ms", createdActor.getActor_id(), duration);

        MDC.clear();
        return ResponseEntity.ok(createdActor);
    }

    // 🟢 UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Update actor by id")
    public ResponseEntity<ActorResponseDTO> updateActor(@PathVariable Integer id,
                                                        @RequestBody ActorRequestDTO actorRequestDTO) {
        long start = System.currentTimeMillis();
        MDC.put("endpoint", "PUT /actors/{id}");
        MDC.put("method", "updateActor");
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("actorId", String.valueOf(id));

        log.info("Received request to update actor id={} with new data: firstName='{}', lastName='{}'",
                id, actorRequestDTO.getFirst_name(), actorRequestDTO.getFirst_name());

        try {
            ActorResponseDTO updatedActor = actorService.updateActor(id, actorRequestDTO);
            long duration = System.currentTimeMillis() - start;
            log.info("Updated actor id={} successfully in {} ms", id, duration);
            return ResponseEntity.ok(updatedActor);
        } catch (RuntimeException e) {
            log.error("Failed to update actor id={}, reason: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } finally {
            MDC.clear();
        }
    }

    // 🟢 DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete actor by id")
    public ResponseEntity<ActorResponseDTO> deleteActor(@PathVariable Integer id) {
        long start = System.currentTimeMillis();
        MDC.put("endpoint", "DELETE /actors/{id}");
        MDC.put("method", "deleteActor");
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("actorId", String.valueOf(id));

        log.info("Received request to delete actor id={}", id);

        ActorResponseDTO deletedActor = actorService.deleteActor(id);
        long duration = System.currentTimeMillis() - start;

        log.info("Deleted actor id={} in {} ms", id, duration);

        MDC.clear();
        return ResponseEntity.ok(deletedActor);
    }
}
