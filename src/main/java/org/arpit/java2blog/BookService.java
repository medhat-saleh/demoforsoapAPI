package org.arpit.java2blog;

import org.arpit.java2blog.book.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private Map<Integer, Book> bookStore = new HashMap<>();
    private int nextId = 1;

    public int createBook(Book book) {
        int id = nextId++;
        book.setId(id);
        bookStore.put(id, book);
        return id;
    }

    public Book getBook(int id) {
        return bookStore.get(id);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookStore.values());
    }

    public void updateBook(Book book) {
        int id = book.getId();
        if (bookStore.containsKey(id)) {
            bookStore.put(id, book);
        }
    }

    public void deleteBook(int id) {
        bookStore.remove(id);
    }
}
