package ru.agrarian.services.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.agrarian.services.back.domain.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
