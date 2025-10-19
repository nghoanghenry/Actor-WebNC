package com.hoang.actorservice.controller;

import com.hoang.actorservice.dto.ActorRequestDTO;
import com.hoang.actorservice.dto.ActorResponseDTO;
import com.hoang.actorservice.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("actors")
@Tag(name = "Actors", description = "API for managing actors")
public class ActorController {
    private final ActorService actorService;
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    @Operation(summary = "Get all actors")
    public ResponseEntity<List<ActorResponseDTO>> getAllActors() {
        List<ActorResponseDTO> actorResponseDTOList = actorService.getAllActor();
        return ResponseEntity.ok(actorResponseDTOList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get actor by id")
    public ResponseEntity<ActorResponseDTO> getActorById(@PathVariable Integer id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @PostMapping
    @Operation(summary = "Create actor")
    public ResponseEntity<ActorResponseDTO> addActor(@RequestBody ActorRequestDTO actorRequestDTO) {
        return ResponseEntity.ok(actorService.createActor(actorRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update actor by id")
    public ResponseEntity<ActorResponseDTO> updateActor(@PathVariable Integer id, @RequestBody ActorRequestDTO actorRequestDTO) {
        try {
            return ResponseEntity.ok(actorService.updateActor(id, actorRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete actor by id")
    public ResponseEntity<ActorResponseDTO> deleteActor(@PathVariable Integer id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok(actorService.deleteActor(id));
    }
}
