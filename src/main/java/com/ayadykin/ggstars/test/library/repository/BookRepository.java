package com.ayadykin.ggstars.test.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ayadykin.ggstars.test.library.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	Optional<Book> findByIsbn(String isbn);
}
