package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT book from Book book where book.amount <= 0")
    List<Book> findNoStockBooks();

    @Query("SELECT book from Book book where book.name LIKE ?1 OR book.description LIKE ?1 OR book.genre LIKE ?1 OR book.author LIKE ?1")
    List<Book> findByDescription(String description);

}
