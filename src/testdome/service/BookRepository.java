package testdome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    public List<String> getBooks() {
        List<String> books = new ArrayList<>();
        books.add("Book");
        books.add("Short book");
        books.add("Long book");

        return books;
    }
}