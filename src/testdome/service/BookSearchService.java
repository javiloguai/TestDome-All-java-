package testdome.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class BookSearchService {

    @Autowired
    public BookRepository bookRepository;

    public boolean bookExists(String bookName) {
        return bookRepository.getBooks().contains(bookName);
    }
}