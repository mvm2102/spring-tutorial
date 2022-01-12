package movie;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceAdapter implements MovieService{

    @Override
    public Movie getByTitle(String title) {
        return new Movie();
    }

    @Override
    public List<Movie> searchByTitle(String title) {
        return new ArrayList<>();
    }

    @Override
    public Movie add(Movie movie) {
        return new Movie();
    }
}
