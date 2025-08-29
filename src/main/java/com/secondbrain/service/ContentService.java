package com.secondbrain.service;

import com.secondbrain.dto.ContentRequest;
import com.secondbrain.model.Content;
import com.secondbrain.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    
    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getUserContent(String userId) {
        return contentRepository.findByUserId(userId);
    }

    public Content createContent(ContentRequest request, String userId) {
        Content content = new Content();
        content.setLink(request.getLink());
        content.setContentType(request.getContentType());
        content.setTitle(request.getTitle());
        content.setTag(request.getTag());
        content.setUserId(userId);
        
        return contentRepository.save(content);
    }

    public void deleteContent(String title, String userId) {
        contentRepository.deleteByTitleAndUserId(title, userId);
    }

    public List<Content> getSharedContent(String userId) {
        return contentRepository.findByUserId(userId);
    }
}


