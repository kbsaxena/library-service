package com.usecase.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usecase.dto.Book;

@FeignClient(value = "book-service-app")
public interface BookService {
	
	@GetMapping("/api/books")
    public List<Book> getAllBooks();
    
    @GetMapping("/api/books/{book_id}")
    public Book getBookById(@PathVariable Long book_id);

    @PostMapping("/api/books")
    public String addBook(@RequestBody Book book);
    
    @DeleteMapping("/api/books/{book_id}")
    public String deleteBook(@PathVariable Long book_id);
    
    @PutMapping("/api/books/{book_id}")
    public String updateBook(@PathVariable Long book_id, @RequestBody Book book);
}
