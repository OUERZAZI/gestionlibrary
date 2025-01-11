package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Book;

import java.util.List;

public interface IServiceBook {
    void addBook(Book book);
    Book getBook(Long id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(Long id);
}