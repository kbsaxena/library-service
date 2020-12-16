package com.usecase.service;

import com.usecase.dto.Book;
import com.usecase.dto.User;
import com.usecase.entity.Library;
import com.usecase.repository.LibraryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LibraryService {
    
    private final String userURL = "http://localhost:8081/api/users";
    private final String bookURL = "http://localhost:7071/api/books";

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    LibraryRepository libraryRepository;
    
    public LibraryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    /*
     * BOOK APIs
     */
    public List<Book> getAllBooks() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(bookURL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
        
        return response.getBody();
    }

    public Book getBookById(Long book_id) {
        ResponseEntity<Book> response = restTemplate.exchange(bookURL+"/"+book_id,
                HttpMethod.GET, null, new ParameterizedTypeReference<Book>() {});
        
        return response.getBody();
    }
    
    public Book addBook(Book book) {
        ResponseEntity<Book> newBook = restTemplate.postForEntity(bookURL, book, Book.class);
        return newBook.getBody();
    }
    
    public void deleteBookById(Long book_id) {
        List<Library> allBooks = libraryRepository.findByBookId(book_id);
        libraryRepository.deleteAll(allBooks);
        
        restTemplate.delete(bookURL+"/"+book_id);
    }
    
    
    /*
     * USER APIs
     */
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(userURL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        
        return response.getBody();
    }
    
    public Map<Long, List<Book>> getUserById(Long user_id) {
        Map<Long, List<Book>> map = new HashMap<>();
        List<Book> books = new ArrayList<>();
        
        ResponseEntity<User> response = restTemplate.exchange(userURL+"/"+user_id,
                HttpMethod.GET, null, new ParameterizedTypeReference<User>() {});
        Long userId = response.getBody().getUserId();
        
        List<Library> lib = libraryRepository.findByUserId(userId);
        
        lib.forEach(library ->{
            books.add(getBookById(library.getBookId()));
        });
        
        map.put(userId, books);
        return map;
    }

    public User addUser(User user) {
        ResponseEntity<User> newUser = restTemplate.postForEntity(userURL, user, User.class);
        return newUser.getBody();
    }
    
    public void deleteUserById(Long user_id) {
        restTemplate.delete(userURL+"/"+user_id);
    }

    public void updateUser(Long user_id, User user) {
        restTemplate.put(userURL+"/"+user_id, user);
    }

    public void issueBookToUser(Long user_id, Long book_id) {
        Library lib = new Library();
        lib.setBookId(book_id);
        lib.setUserId(user_id);
        libraryRepository.save(lib);
    }

    public void deleteBookUserRel(Long user_id, Long book_id) {
        Library library = libraryRepository.findByBookIdAndUserId(book_id, user_id);
        libraryRepository.deleteById(library.getLibraryId());
    }
    
}
