package com.mafort.forum.controller;

import com.mafort.forum.domain.author.Author;
import com.mafort.forum.domain.author.AuthorDTO;
import com.mafort.forum.domain.author.AuthorRepository;
import com.mafort.forum.domain.author.AuthorResponseDTO;
import com.mafort.forum.domain.topic.TopicResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorRepository repository;
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> add(@RequestBody @Valid AuthorDTO authorDTO, UriComponentsBuilder uriBuilder){
        Author author = new Author(authorDTO);
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        repository.save(author);
        var uri = uriBuilder.path("topics/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(uri).body(new AuthorResponseDTO(author.getName(), authorDTO.email()));
    }
    @SecurityRequirement(name = "bearer-key")
    @GetMapping
    public ResponseEntity<Page<AuthorResponseDTO>> list(@PageableDefault(size = 10) Pageable pagination){
        var page = repository.findAll(pagination).map(AuthorResponseDTO::new);
        return ResponseEntity.ok(page);
    }
}
