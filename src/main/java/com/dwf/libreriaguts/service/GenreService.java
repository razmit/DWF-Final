package com.dwf.libreriaguts.service;

import com.dwf.libreriaguts.dto.GenreDto;
import com.dwf.libreriaguts.entity.Author;
import com.dwf.libreriaguts.entity.Genre;
import com.dwf.libreriaguts.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final IGenreRepository genreRepository;

    @Autowired
    public GenreService(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Convert Genre entity to DTO
    private GenreDto convertToDTO(Genre genre) {
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }

    // Convert Genre DTO to entity
    private Genre convertToEntity(GenreDto dto) {
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        return genre;
    }

//    // Get all genres
//    public List<GenreDto> getAllGenres() {
//        return genreRepository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Get genre by ID
//    public GenreDto getGenreById(Long id) {
//        return genreRepository.findById(id)
//                .map(this::convertToDTO)
//                .orElse(null);
//    }

//    // Save or update a genre
//    public void saveGenre(GenreDto genreDTO) {
//        Genre genre = convertToEntity(GenreDto);
//        genreRepository.save(convertToEntity(genre));
//    }

    // Delete a genre
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
