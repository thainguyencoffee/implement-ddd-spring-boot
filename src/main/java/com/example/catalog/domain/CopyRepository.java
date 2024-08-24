package com.example.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Copy.CopyIdentifier> {
}
