package com.mafort.forum.domain.topic;

public record TopicResponseDTO(Long id, String title, String message) {
    public TopicResponseDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage());
    }
}
