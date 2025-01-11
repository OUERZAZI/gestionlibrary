package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Book;
import com.polytec.gestionlibrary.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServiceBook implements IServiceBook {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook(Book book) {
        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found for update");
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
