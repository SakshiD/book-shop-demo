package bootcamp;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
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

    private static Book toBook(BookEntity bookEntity) {
        return new Book(bookEntity.name, bookEntity.author);
    }

    public BookResponse addBook(Book book) {
        String uuid = UUID.randomUUID().toString();
        BookEntity bookEntity = new BookEntity(book.name(), book.author(), uuid);
        BookEntity bookEntityFromDB = bookRepository.save(bookEntity);
        BookResponse bookResponse;
        if(bookEntityFromDB != null) {
            bookResponse = new BookResponse(bookEntityFromDB.uuid, bookEntityFromDB.name, bookEntityFromDB.author);
            return bookResponse;
        }
        return null;
    }
}
