package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getBookById(@PathVariable(value="id") Long id)
    {
        return bookRepository.findById(id);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book)
    {
        return bookRepository.save(book);
    }

    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable(value="id") Long id, @RequestBody Book bookDetails)
    {
        Optional<Book> book = bookRepository.findById(id);
        Book book_new=book.get();
        book_new.setAuthor_name(bookDetails.getAuthor_name());
        book_new.setBook_name(bookDetails.getBook_name());
        book_new.setIsbn(bookDetails.getIsbn());
        return bookRepository.save(book_new);

    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable(value="id") Long id)
    {
        Optional<Book> book=bookRepository.findById(id);
        Book book_new=book.get();
        bookRepository.delete(book_new);
    }
}
