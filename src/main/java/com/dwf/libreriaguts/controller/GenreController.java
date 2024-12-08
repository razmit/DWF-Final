package com.dwf.libreriaguts.controller;

import com.dwf.libreriaguts.dto.GenreDto;
import com.dwf.libreriaguts.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("pageContent", "genres/list");
        return "layout";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("pageTitle", "Add new genre");
        model.addAttribute("genre", new GenreDto());
        model.addAttribute("pageContent", "genres/add");
        return "layout";
    }

    @PostMapping
    public String saveGenre(@ModelAttribute("genre") GenreDto genreDTO) {
        genreService.saveGenre(genreDTO);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Edit Genre");
        model.addAttribute("genre", genreService.getGenreById(id));
        model.addAttribute("pageContent", "genres/edit");
        return "layout";
    }

    @PostMapping("/{id}")
    public String updateGenre(@PathVariable Long id, @ModelAttribute("genre") GenreDto genreDTO) {
        genreDTO.setId(id);
        genreService.saveGenre(genreDTO);
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }
}
