package ru.agrarian.services.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.agrarian.services.back.domain.Header;

public interface HeaderRepository extends JpaRepository<Header, Integer> {
}
