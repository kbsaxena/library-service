package com.usecase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usecase.dto.Book;
import com.usecase.dto.User;
import com.usecase.entity.Library;
import com.usecase.repository.LibraryRepository;

@Service
public class LibraryService {
    
    @Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	 @Autowired
	 LibraryRepository libraryRepository;
    
    /*
     * BOOK APIs
     */
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    

    public Book getBookById(Long book_id) {
        return bookService.getBookById(book_id);
    }
    
    public Book addBook(Book book) {
        bookService.addBook(book);
        return book;
    }
    
    public void deleteBookById(Long book_id) {
        bookService.deleteBook(book_id);
    }
    
    
    /*
     * USER APIs
     */
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    public Map<Long, List<Book>> getUserById(Long user_id) {
        Map<Long, List<Book>> map = new HashMap<>();
        List<Book> books = new ArrayList<>();
        
        List<Library> lib = libraryRepository.findByUserId(user_id);
        
        lib.forEach(library ->{
            books.add(getBookById(library.getBookId()));
        });
        
        map.put(user_id, books);
        return map;
    }

    public User addUser(User user) {
        userService.addUser(user);
        return user;
    }
    
    public void deleteUserById(Long user_id) {
    	userService.deleteUser(user_id);
    }

    public void updateUser(Long user_id, User user) {
        userService.updateUser(user_id, user);
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
