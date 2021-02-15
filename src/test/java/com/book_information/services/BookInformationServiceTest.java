package com.book_information.services;

import com.book_information.models.BookInformation;
import com.book_information.repositories.BookInformationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BookInformationServiceTest {
    @Mock
    private BookInformationRepository bookInformationRepository;
    @InjectMocks
    private BookInformationService bookInformationService;

    private final BookInformation book1 = new BookInformation("book1", "title1", "author1", "url1", 500);
    private final BookInformation book2 = new BookInformation("book2", "title2", "author2", "url2", 200);
    private final BookInformation book3 = new BookInformation("book3", "title3", "author2", "url3", 700);
    private final BookInformation book4 = new BookInformation("book4", "title4", "author4", "url4", 1200);
    private final BookInformation book5 = new BookInformation("book5", "title5", "author5", "url5", 100);
    private final BookInformation book6 = new BookInformation("book6", "title6", "author6", "url6", 400);

    @Test
    public void shouldBeEmptyWhenNoBooks() {
        Mockito.when(bookInformationRepository.findByTitleContaining("someName")).thenReturn(new ArrayList<>());

        List<BookInformation> books = bookInformationService.getBooksBy("someName");
        assertTrue(books.isEmpty());
    }

    @Test
    public void shouldReturnMaxFiveBooksBasedOnPublishedDate() {
        List<BookInformation> mockData = Arrays.asList(book1, book2, book3, book4, book5, book6);
        Mockito.when(bookInformationRepository.findByTitleContaining("title")).thenReturn(mockData);

        List<BookInformation> actualBooks = bookInformationService.getBooksBy("title");
        assertEquals(5, actualBooks.size());

        List<BookInformation> expectedBooks = Arrays.asList(book4, book3, book1, book6, book2);
        assertEquals(expectedBooks, actualBooks);
    }
}
