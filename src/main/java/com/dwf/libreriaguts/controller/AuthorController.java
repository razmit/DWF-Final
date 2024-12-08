package com.dwf.libreriaguts.controller;

import com.dwf.libreriaguts.dto.AuthorsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.dwf.libreriaguts.service.AuthorService;

import java.util.List;


@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model) {
        List<AuthorsDto> authors = authorService.getAllAuthors(); // Fetch data from DB
        model.addAttribute("authors", authors);
        model.addAttribute("pageContent", "authors/list");
        return "layout";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new AuthorsDto());
        model.addAttribute("pageContent", "authors/add");
        return "layout";
    }

    @PostMapping
    public String saveAuthor(@ModelAttribute("author") AuthorsDto authorDTO) {
        authorService.saveAuthor(authorDTO);
        return "redirect:/authors/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("pageContent", "authors/edit");
        return "layout";
    }

    @PostMapping("/{id}")
    public String updateAuthor(@PathVariable Long id, @ModelAttribute("author") AuthorsDto authorDTO) {
        authorDTO.setId(id);
        authorService.saveAuthor(authorDTO);
        return "redirect:/authors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors/list";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }
}
