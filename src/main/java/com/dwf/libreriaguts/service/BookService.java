package com.dwf.libreriaguts.service;

import com.dwf.libreriaguts.dto.BooksDto;
import com.dwf.libreriaguts.entity.Author;
import com.dwf.libreriaguts.entity.Book;
import com.dwf.libreriaguts.entity.Genre;
import com.dwf.libreriaguts.repository.IAuthorRepository;
import com.dwf.libreriaguts.repository.IBookRepository;
import com.dwf.libreriaguts.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final IGenreRepository genreRepository;

    @Autowired
    public BookService(IBookRepository bookRepository, IAuthorRepository authorRepository, IGenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    // Convert Book entity to DTO
    private BooksDto convertToDTO(Book book) {
        BooksDto dto = new BooksDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPublishedYear(book.getPublishedYear());
        dto.setAvailableCopies(book.getAvailableCopies());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setGenreIds(book.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));

        return dto;
    }

    private Book convertToEntity(BooksDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setPublishedYear(dto.getPublishedYear());
        book.setAvailableCopies(dto.getAvailableCopies());

        // Fetch and set the Author entity
        book.setAuthor(authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found")));

        // Fetch and set the Genre entities
        Set<Genre> genres = dto.getGenreIds().stream()
                .map(id -> genreRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Genre not found")))
                .collect(Collectors.toSet());
        book.setGenres(genres);

        return book;
    }

    // Get all books
    public List<BooksDto> getAllBooks() {
        return bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get book by ID
    public BooksDto getBookById(Long id) {
        return bookRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    // Save or update book
    @Transactional
    public BooksDto saveBook(BooksDto bookDTO) {
        System.out.println("Converting DTO to Entity...");
        Book book = convertToEntity(bookDTO);
        System.out.println("Book Genres: " + book.getGenres());
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }


    // Delete book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
