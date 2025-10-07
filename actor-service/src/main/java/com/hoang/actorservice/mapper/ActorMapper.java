package com.hoang.actorservice.mapper;

import com.hoang.actorservice.dto.ActorRequestDTO;
import com.hoang.actorservice.dto.ActorResponseDTO;
import com.hoang.actorservice.model.Actor;

public class ActorMapper {
    public static Actor toActor(ActorRequestDTO actorRequestDTO) {
        Actor actor = new Actor();
        actor.setFirst_name(actorRequestDTO.getFirst_name());
        actor.setLast_name(actorRequestDTO.getLast_name());
        return actor;
    }

    public static ActorResponseDTO toActorResponseDTO(Actor actor) {
        ActorResponseDTO actorResponseDTO = new ActorResponseDTO();
        actorResponseDTO.setActor_id(actor.getActor_id().toString());
        actorResponseDTO.setFirst_name(actor.getFirst_name());
        actorResponseDTO.setLast_name(actor.getLast_name());
        actorResponseDTO.setLast_update(actor.getLast_update().toString());
        return actorResponseDTO;
    }
}
