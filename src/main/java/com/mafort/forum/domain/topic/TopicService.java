package com.mafort.forum.domain.topic;

import com.mafort.forum.domain.author.Author;
import com.mafort.forum.domain.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Topic createTopic(TopicDTO topicDTO) {
        Author author = authorRepository.findById(topicDTO.idAuthor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        System.out.println(author);

         Topic topic = new Topic(null,topicDTO.title(), topicDTO.message(), true, LocalDateTime.now(),author, topicDTO.nameCourse());
        if (topicRepository.existsByTitleAndMessage(topic.getTitle(), topic.getMessage())) {
            throw new RuntimeException("Erro! Não é possível ter tópicos duplicados.");
        }
        topicRepository.save(topic);
        return topic;
    }

}
