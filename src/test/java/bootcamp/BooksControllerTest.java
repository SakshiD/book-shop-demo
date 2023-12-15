package bootcamp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
public class BooksControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void getBooksTest_EmptyList() throws Exception {
        Book books = new Book("","");
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
}
