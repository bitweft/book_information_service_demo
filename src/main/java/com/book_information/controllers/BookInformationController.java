package com.book_information.controllers;

import com.book_information.models.BookInformation;
import com.book_information.services.BookInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookInformationController {
    @Autowired
    BookInformationService bookInformationService;

    @GetMapping(path = "/search", produces = {"application/json;charset=UTF-8"})
    public List<BookInformation> getBookInformation(@RequestParam(name = "title") String title) {
        return bookInformationService.getBooksBy(title);
    }
}
