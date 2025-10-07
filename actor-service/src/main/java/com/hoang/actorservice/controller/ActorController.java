package com.hoang.actorservice.controller;

import com.hoang.actorservice.dto.ActorRequestDTO;
import com.hoang.actorservice.dto.ActorResponseDTO;
import com.hoang.actorservice.model.Actor;
import com.hoang.actorservice.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("actors")
public class ActorController {
    private final ActorService actorService;
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<ActorResponseDTO>> getAllActors() {
        List<ActorResponseDTO> actorResponseDTOList = actorService.getAllActor();
        return ResponseEntity.ok(actorResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> getActorById(@PathVariable Integer id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @PostMapping
    public ResponseEntity<ActorResponseDTO> addActor(@RequestBody ActorRequestDTO actorRequestDTO) {
        return ResponseEntity.ok(actorService.createActor(actorRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> updateActor(@PathVariable Integer id, @RequestBody ActorRequestDTO actorRequestDTO) {
        try {
            return ResponseEntity.ok(actorService.updateActor(id, actorRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> deleteActor(@PathVariable Integer id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok(actorService.deleteActor(id));
    }
}
