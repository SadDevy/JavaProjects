package com.translatorapi.repositories;

import com.translatorapi.models.TranslateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRequestRepository
        extends JpaRepository<TranslateRequest, Long> {
}
