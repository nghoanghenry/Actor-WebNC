package com.hoang.actorservice.service;

import com.hoang.actorservice.dto.ActorRequestDTO;
import com.hoang.actorservice.dto.ActorResponseDTO;
import com.hoang.actorservice.mapper.ActorMapper;
import com.hoang.actorservice.model.Actor;
import com.hoang.actorservice.repository.ActorRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public ActorResponseDTO getActorById(Integer id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return null;
        }
        return ActorMapper.toActorResponseDTO(actor);

    }

    public ActorResponseDTO createActor(ActorRequestDTO actorRequestDTO) {
        Actor newActor = actorRepository.save(ActorMapper.toActor(actorRequestDTO));
        return ActorMapper.toActorResponseDTO(newActor);
    }

    public ActorResponseDTO updateActor(Integer id, ActorRequestDTO actorRequestDTO) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return null;
        }
        actor.setFirst_name(actorRequestDTO.getFirst_name());
        actor.setLast_name(actorRequestDTO.getLast_name());
        actor.setLast_update(LocalDateTime.now());
        Actor updatedActor = actorRepository.save(actor);
        return ActorMapper.toActorResponseDTO(updatedActor);
    }

    public ActorResponseDTO deleteActor(Integer id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return null;
        }
        actorRepository.delete(actor);
        return ActorMapper.toActorResponseDTO(actor);
    }

    public List<ActorResponseDTO> getAllActor() {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream().map(ActorMapper::toActorResponseDTO).toList();
    }
}
