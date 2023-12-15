package bootcamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
