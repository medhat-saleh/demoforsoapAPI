package org.arpit.java2blog;

import jakarta.annotation.PostConstruct;
import org.arpit.java2blog.book.Book;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookRepository {
    private static final Map<Integer, Book> books = new HashMap<>();
    private int nextId = 1;

    @PostConstruct
    public void initData() {

        Book  javaBook = new Book();
        javaBook.setId(1);
        nextId++;
        javaBook.setTitle("Head first java");
        javaBook.setPages(400);
        books.put(javaBook.getId(), javaBook);

        Book springBook = new Book();
        springBook.setId(2);
        nextId++;
        springBook.setTitle("Spring in action");
        springBook.setPages(400);
        books.put(springBook.getId(), springBook);

        Book pythonBook = new Book();
        pythonBook.setId(3);
        nextId++;
        pythonBook.setTitle("Learning Python");
        pythonBook.setPages(400);
        books.put(pythonBook.getId(), pythonBook);

        Book hiberanteBook = new Book();
        hiberanteBook.setId(4);
        nextId++;
        hiberanteBook.setTitle("Hibernate in action");
        hiberanteBook.setPages(400);
        books.put(hiberanteBook.getId(), hiberanteBook);
    }

    public Book findBookById(int id) {
        Assert.notNull(id, "The book's name must not be null");
        return books.get(id);
    }
    public int saveBook(Book book) {
        int id = nextId++;
        book.setId(id);
        books.put(id, book);
        return id;
    }



    public boolean updateBook(Book book) {
        int id = book.getId();
        if (books.containsKey(id)) {
            books.put(id, book);
            return  true;
        }
        return false;
    }

    public boolean deleteBook(int id) {
        books.remove(id);
        return true;
    }
}
