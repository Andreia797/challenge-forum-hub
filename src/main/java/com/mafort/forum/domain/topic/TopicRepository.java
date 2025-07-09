package com.mafort.forum.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("SELECT COUNT(t) > 0 FROM Topic t WHERE t.title = :title AND t.message = :message")
    boolean existsByTitleAndMessage(String title, String message);
}
