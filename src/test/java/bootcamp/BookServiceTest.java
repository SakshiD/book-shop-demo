package bootcamp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Test
    void shouldGetBooksFromDB() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(new BookEntity(1, "java", "Herbert")));
        BookService bookService = new BookService((bookRepository));

        Book expectedBook = new Book("java", "Herbert");
        List<Book> books = bookService.allBooks();
        assertEquals(expectedBook, books.get(0));
    }
}
