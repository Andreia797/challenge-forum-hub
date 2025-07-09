package com.mafort.forum.domain.topic;

import com.mafort.forum.domain.author.Author;
import com.mafort.forum.domain.author.AuthorDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long idAuthor,
        @NotBlank
        String nameCourse){
}