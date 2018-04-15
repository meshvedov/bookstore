package com.bookstore.service.impl;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();

        List<Book> activeBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.isActive())
                activeBooks.add(book);
        }
        return activeBooks;
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> blurrySearch(String keyWord) {
        List<Book> bookList = bookRepository.findByTitleContaining(keyWord);

        List<Book> activeBooks = new ArrayList<>();

        bookList.forEach(book -> {
            if (book.isActive())
                activeBooks.add(book);
        });

        return activeBooks;
    }

    @Override
    public void removeOne(Long id) {
        bookRepository.delete(id);
    }
}
