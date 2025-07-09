package com.mafort.forum.controller;

import com.mafort.forum.domain.topic.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicRepository repository;

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponseDTO> add(@RequestBody @Valid TopicDTO topicDTO, UriComponentsBuilder uriBuilder) {
        Topic topic = topicService.createTopic(topicDTO);
        var uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicResponseDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> list(@PageableDefault(size = 10, sort = "createdAt") Pageable pagination) {
        var page = repository.findAll(pagination).map(TopicResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicResponseDTO> getTopic(@PathVariable Long id) {
        var topic = repository.findById(id);
        if (topic.isEmpty()) {
            throw new RuntimeException("Erro! Não existe tópico com este id");
        }
        return ResponseEntity.ok(new TopicResponseDTO(topic.get()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> update(@RequestBody @Valid TopicUpdateDTO topicUpdateDTO) {
        Optional<Topic> topicOptional = repository.findById(topicUpdateDTO.id());
        if (topicOptional.isEmpty()) {
            throw new RuntimeException("Erro! Não existe tópico com este id");
        }
        Topic topic = topicOptional.get();
        topic.update(topicUpdateDTO);
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Topic> topicOptional = repository.findById(id);
        if (topicOptional.isEmpty()) {
            throw new RuntimeException("Erro! Não existe tópico com este id");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
