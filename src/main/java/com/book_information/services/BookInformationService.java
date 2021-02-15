package com.book_information.services;

import com.book_information.models.BookInformation;
import com.book_information.repositories.BookInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.min;

@Service
public class BookInformationService {
    @Autowired
    private BookInformationRepository bookInformationRepository;

    public List<BookInformation> getBooksBy(String title) {
        List<BookInformation> books = bookInformationRepository.findByTitleContaining(title);
        books.sort(Comparator.reverseOrder());

        int maximumAllowedBooks = 5;
        return books.subList(0, min(books.size(), maximumAllowedBooks));
    }
}
