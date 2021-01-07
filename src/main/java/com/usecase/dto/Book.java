package com.usecase.dto;

import lombok.Data;

@Data
public class Book {
    
    private Long bookId;
    private String title;
    private String author;
    private String category;
    private String description;
}
