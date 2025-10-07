package com.hoang.actorservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ActorRequestDTO {
    public @NotBlank(message = "First Name is required") @Size(max = 45, message = "First Name can not exceed 45 characters") String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@NotBlank(message = "First Name is required") @Size(max = 45, message = "First Name can not exceed 45 characters") String first_name) {
        this.first_name = first_name;
    }

    public @NotBlank(message = "Last Name is required") @Size(max = 45, message = "Last Name can not exceed 45 characters") String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NotBlank(message = "Last Name is required") @Size(max = 45, message = "Last Name can not exceed 45 characters") String last_name) {
        this.last_name = last_name;
    }

    @NotBlank(message = "First Name is required")
    @Size(max = 45, message = "First Name can not exceed 45 characters")
    String first_name;

    @NotBlank(message = "Last Name is required")
    @Size(max = 45, message = "Last Name can not exceed 45 characters")
    String last_name;
}
