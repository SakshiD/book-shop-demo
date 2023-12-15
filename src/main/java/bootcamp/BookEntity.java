package bootcamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="BOOK")
public class BookEntity {

    @Id
    Integer bookId;
    String name;
    String author;

    public BookEntity() {
    }

    public BookEntity(Integer bookId, String name, String author) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
    }


}
