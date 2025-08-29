package com.secondbrain.repository;

import com.secondbrain.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {
    List<Content> findByUserId(String userId);
    void deleteByTitleAndUserId(String title, String userId);
}


