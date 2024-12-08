package com.dwf.libreriaguts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class AuthorsDto {

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name cannot be empty") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name cannot be empty") String name) {
        this.name = name;
    }
}

