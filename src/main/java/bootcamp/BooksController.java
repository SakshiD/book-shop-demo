package bootcamp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BooksController {


    private BookService bookService;


    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<Books> books() {
        return ResponseEntity.ok(new Books(bookService.allBooks()));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Books> books(@RequestBody Book book) throws URISyntaxException {

        System.out.println("book "+book);
        BookResponse bookResponse = bookService.addBook(book);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, bookResponse.uuid());

        return new ResponseEntity(
                bookResponse,
                httpHeaders,
                HttpStatus.CREATED
        );
    }

}
