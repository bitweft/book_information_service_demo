package com.book_information.controllers;

import com.book_information.models.BookInformation;
import com.book_information.services.BookInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookInformationController.class)
public class BookInformationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookInformationService bookInformationService;

    @Test
    public void shouldReturnEmptyWhenNoBooks() throws Exception {
        when(bookInformationService.getBooksBy("someTitle")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/search").param("title", "someTitle"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    public void shouldReturnBooksInformation() throws Exception {
        List<BookInformation> books = getMockBooksInformation();
        when(bookInformationService.getBooksBy("someTitle")).thenReturn(books);

        mockMvc.perform(get("/search").param("title", "someTitle"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getExpectedBookInformationResponse()));
    }

    private List<BookInformation> getMockBooksInformation() {
        BookInformation book1 = new BookInformation("book-1", "title-1", "author-1", 100);
        BookInformation book2 = new BookInformation("book-2", "title-2", "author-2", 200);
        return List.of(book1, book2);
    }

    private String getExpectedBookInformationResponse() {
        return """
                        [
                          {
                            "bookId": "book-1",
                            "title": "title-1",
                            "author": "author-1",
                            "publishDate": 100
                          },
                          {
                            "bookId": "book-2",
                            "title": "title-2",
                            "author": "author-2",
                            "publishDate": 200
                          }
                        ]
                """;
    }
}
