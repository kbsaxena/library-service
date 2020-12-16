package com.usecase.controller;

import com.usecase.dto.Book;
import com.usecase.dto.User;
import com.usecase.service.LibraryService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LibraryController {
    
    @Autowired
    LibraryService libraryService;
    
    @GetMapping("/library")
    public List<User> getLibrary(){
        return null; //GET /users/{user_id}
    }
    
    @GetMapping("/library/books")
    public List<Book> getLibraryBooks(){
        return libraryService.getAllBooks();
    }
    
    @GetMapping("/library/books/{book_id}")
    public Book getLibraryBookByBookId(@PathVariable Long book_id){
        return libraryService.getBookById(book_id);
    }
    
    @PostMapping("/library/books")
    public Book addNewBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }
    
    @DeleteMapping("/library/books/{book_id}")
    public String deleteBookByBookId(@PathVariable Long book_id){
        libraryService.deleteBookById(book_id);
        return "Book Deleted Successfully with ID - " + book_id;
    }
    
    /* USER */
    @GetMapping("/library/users")
    public List<User> getLibraryUsers(){
        return libraryService.getAllUsers();
    }
    
    @GetMapping("/library/users/{user_id}")
    public Map<Long, List<Book>> getLibraryUserByUserId(@PathVariable Long user_id){
        return libraryService.getUserById(user_id);
    }
    
    @PostMapping("/library/users")
    public User addNewUser(@RequestBody User user){
        return libraryService.addUser(user);
    }
    
    @DeleteMapping("/library/users/{user_id}")
    public String deleteUserByUserId(@PathVariable Long user_id){
        libraryService.deleteUserById(user_id);
        return "User Deleted Successfully with ID - " + user_id;
    }
    
    @PutMapping("/library/users/{user_id}")
    public String updateUser(@PathVariable Long user_id, @RequestBody User user) {
        libraryService.updateUser(user_id, user);
        return "User Updated Successfully with ID - " + user_id;
    }
    
    @PostMapping("/library/users/{user_id}/books/{book_id}")
    public String issueBookToUser(@PathVariable Long user_id, @PathVariable Long book_id) {
        libraryService.issueBookToUser(user_id, book_id);
        return "Book issued to User Successfully";
    }
    
    @DeleteMapping("/library/users/{user_id}/books/{book_id}")
    public String deleteBookUserRel(@PathVariable Long user_id, @PathVariable Long book_id) {
        libraryService.deleteBookUserRel(user_id, book_id);
        return "Book removed from Users Name";
    }
}
