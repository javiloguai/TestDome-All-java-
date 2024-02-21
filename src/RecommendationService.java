import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;

@Configuration
class BookSearch{
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    public RecommendationService recommendationService() {
        return new RecommendationService();
    }
}

class RecommendationService {
    public BookRepository repository;

    public String recommendBook() {
        return repository.getBooks().get(0);
    }
}

class BookRepository {
    public List<String> getBooks() {
        List<String> books = new ArrayList<>();
        books.add("Book");
        books.add("Short book");
        books.add("Long book");

        return books;
    }
}