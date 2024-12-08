package com.dwf.libreriaguts.repository;

import com.dwf.libreriaguts.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends JpaRepository<Author, Long> {
}
