package com.baeldung.hexagonalarchitecture;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public BookGenre getMostPopularGenre() {
        List<Book> books = bookRepository.findAll();
        Map<BookGenre, List<Book>> booksByGenre = books.stream()
          .collect(Collectors.groupingBy(Book::getGenre));

        BookGenre mostPopularGenre = 
          booksByGenre.entrySet()
          .stream()
          .max(Comparator.comparingInt(entry -> entry.getValue().size()))
          .map(Map.Entry::getKey)
          .orElse(null);

        return mostPopularGenre;
    }

    public Book getMostReadBook() {
        List<Book> books = bookRepository.findAll();
        Book mostReadBook = 
          books.stream()
            .max(Comparator.comparingInt(Book::getNumberOfReaders))
            .orElse(null);
        return mostReadBook;
    }
}
