package bootcamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="BOOK")
public class BookEntity {

    @Id
    @GeneratedValue
    Integer bookId;
    String name;
    String author;
    String uuid;

    public BookEntity() {
    }

    public BookEntity(String name, String author, String uuid) {
        this.name = name;
        this.author = author;
        this.uuid = uuid;
    }


}
