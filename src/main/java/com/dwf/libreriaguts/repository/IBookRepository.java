package com.dwf.libreriaguts.repository;

import com.dwf.libreriaguts.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {

}
