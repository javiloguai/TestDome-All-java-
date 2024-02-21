import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class MovieRowMapper implements RowMapper < Movie > {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();

        movie.setName(rs.getString("name"));
        movie.setRating(rs.getInt("rating"));
        movie.setYear(rs.getInt("year"));
        return movie;
    }
}
class Movie {
    public int id;
    public String name;
    public int rating;
    public int year;

    public Movie() {

    }
    public Movie(String name, int year, int rating) {
        this.name = name;
        this.year = year;
        this.rating = rating;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", rating=" + rating + ", year=" + year + "]";
    }


}

@Configuration
@Import(MovieRepository.class)
class Config {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        return ds;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
        return new JdbcTemplate(ds);
    }
}


@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate template;

    @PostConstruct
    public void createTable() {
        template.execute("CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, rating int)");
    }

    public void createMovie(String name, int year, int rating) {
        template.update("insert into movies (name, year, rating) " + "values(?, ?, ?)",
                new Object[] {
                        name, year, rating    });
    }

    public List<Movie> findMoviesByName(String likeName) {
        List<Movie> movies = template.query("SELECT * FROM movies where name like ?", new MovieRowMapper(),likeName);
        return movies;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        config.register(Config.class);
        config.refresh();
        MovieRepository repository = config.getBean(MovieRepository.class);

        repository.createMovie("Some movie", 1974, 3);
        repository.createMovie("Some other movie", 1993, 2);

        List<Movie> movies = repository.findMoviesByName("Some%");
        for(Movie movie : movies) {
            System.out.println(movie.name + " - " + movie.year + " - " + movie.rating);
        }
    }
}




