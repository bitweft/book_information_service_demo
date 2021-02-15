package com.book_information.repositories;

import com.book_information.models.BookInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookInformationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookInformationRepository bookInformationRepository;

    private BookInformation book1;
    private BookInformation book2;
    private BookInformation book3;

    @Before
    public void setup() {
        setupTestData();
    }

    @Test
    public void shouldReturnAllBooksMatchingTitle() {
        List<BookInformation> books = bookInformationRepository.findByTitleContaining("Harry");
        assertEquals(2, books.size());

        List<BookInformation> expectedBooks = List.of(book1, book2);
        assertTrue(expectedBooks.containsAll(books));

    }

    @Test
    public void shouldIgnoreCaseWhileSearchingWithBookTitle() {
        List<BookInformation> books = bookInformationRepository.findByTitleContaining("lord Of THE");
        assertEquals(1, books.size());

        List<BookInformation> expectedBooks = List.of(book3);
        assertTrue(expectedBooks.containsAll(books));
    }

    @Test
    public void shouldBeEmptyWhenNoMatchingBookTitle() {
        List<BookInformation> books = bookInformationRepository.findByTitleContaining("NotPresentBook");
        assertTrue(books.isEmpty());
    }

    private void setupTestData() {
        book1 = new BookInformation("id-1", "Harry Potter 1", "JK Rowling", 867283200);
        book2 = new BookInformation("id-2", "Harry Potter 2", "JK Rowling", 899337600);
        book3 = new BookInformation("id-3", "The Lord of the Rings 1", "JRR Tolkien", -487123200);
        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.flush();
    }
}
