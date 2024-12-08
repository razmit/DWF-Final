package com.dwf.libreriaguts.controller;

import com.dwf.libreriaguts.dto.BooksDto;
import com.dwf.libreriaguts.service.AuthorService;
import com.dwf.libreriaguts.service.BookService;
import com.dwf.libreriaguts.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("pageContent", "books/list");
        return "layout";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new BooksDto());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("pageContent", "books/add");
        return "layout";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") BooksDto bookDTO) {
        System.out.println("Saving Book: " + bookDTO.getTitle());
        System.out.println("Author ID: " + bookDTO.getAuthorId());
        System.out.println("Genre IDs: " + bookDTO.getGenreIds());
        bookService.saveBook(bookDTO);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("pageTitle", "Edit Book");
        model.addAttribute("pageContent", "books/edit");
        return "layout";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") BooksDto bookDTO) {
        bookDTO.setId(id);
        bookService.saveBook(bookDTO);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
