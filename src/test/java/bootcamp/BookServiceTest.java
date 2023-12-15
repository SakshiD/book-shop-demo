package bootcamp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Test
    void shouldGetBooksFromDB() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(new BookEntity("java", "Herbert","uuid")));
        BookService bookService = new BookService((bookRepository));

        Book expectedBook = new Book("java", "Herbert");
        List<Book> books = bookService.allBooks();
        assertEquals(expectedBook, books.get(0));
    }

    @Test
    void shouldAddBookInDB() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        String uuid = UUID.randomUUID().toString();
        Book book = new Book("java","Herbert");
        BookEntity bookEntity = new BookEntity("java", "Herbert",uuid);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);
        BookService bookService = new BookService((bookRepository));
        BookResponse bookResponse = bookService.addBook(book);
        assertNotNull(bookResponse.uuid());
    }

    @Test
    void shouldAddBookInDB_whenNull() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        String uuid = UUID.randomUUID().toString();
        Book book = new Book("java","Herbert");
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(null);
        BookService bookService = new BookService((bookRepository));
        BookResponse bookResponse = bookService.addBook(book);
        assertNull(bookResponse);
    }
}
