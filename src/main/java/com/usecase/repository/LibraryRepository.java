package com.usecase.repository;

import com.usecase.entity.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findByBookIdAndUserId(Long book_id, Long user_id);
    List<Library> findByUserId(Long user_id);
    List<Library> findByBookId(Long book_id);
}
