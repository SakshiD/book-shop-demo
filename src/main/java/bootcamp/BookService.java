package bootcamp;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks() {
        return bookRepository.findAll().stream().map(b -> toBook(b)).collect(Collectors.toList());
    }

    private static Book toBook(BookEntity bookEntity){
        return new Book(bookEntity.name,bookEntity.author);
    }

}
