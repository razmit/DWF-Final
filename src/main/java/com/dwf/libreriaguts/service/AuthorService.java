package com.dwf.libreriaguts.service;

import com.dwf.libreriaguts.dto.AuthorsDto;
import com.dwf.libreriaguts.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwf.libreriaguts.repository.IAuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final IAuthorRepository authorRepository;

    @Autowired
    public AuthorService(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Convert Author to AuthorDTO
    private AuthorsDto convertToDTO(Author author) {
        AuthorsDto dto = new AuthorsDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    // Convert AuthorDTO to Author
    private Author convertToEntity(AuthorsDto dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        return author;
    }

    public List<AuthorsDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AuthorsDto getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AuthorsDto saveAuthor(AuthorsDto authorDTO) {
        Author author = convertToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return convertToDTO(savedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
