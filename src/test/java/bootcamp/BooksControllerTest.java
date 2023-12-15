package bootcamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
public class BooksControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    @Test
    public void getBooksTest_EmptyList() throws Exception {
        Book books = new Book("", "");
        Mockito.when(bookService.allBooks())
                .thenReturn(List.of(books));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books").isArray());
    }

    @Test
    public void getBooksTest_GetBook() throws Exception {
        Book book = new Book("Java", "Herbert");
        Books books = new Books(List.of(book));
        Mockito.when(bookService.allBooks())
                .thenReturn(List.of(book));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books.[0].name").value("Java"))
                .andExpect(jsonPath("$.books.[0].author").value("Herbert"));
    }

    @Test
    public void addBookSuccessfully() throws Exception {
        Book book = new Book("Java", "Herbert");
        String uuid = UUID.randomUUID().toString();
        System.out.println(objectMapper.writeValueAsString(book));
        BookResponse bookResponse = new BookResponse(uuid, "Java", "Herbert");
        Mockito.when(bookService.addBook(book))
                .thenReturn(bookResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(uuid)))
                .andExpect(content().string(objectMapper.writeValueAsString(bookResponse)));
        ;
    }

}
