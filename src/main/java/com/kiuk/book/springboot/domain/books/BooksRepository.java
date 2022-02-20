package com.kiuk.book.springboot.domain.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

    @Query("SELECT b FROM Books b ORDER BY b.id DESC")
    List<Books> findAllBookDesc();

    @Query("SELECT b FROM Books b WHERE b.title LIKE %:keyword% ")
    List<Books> findAllBookSearch(String keyword);

}
