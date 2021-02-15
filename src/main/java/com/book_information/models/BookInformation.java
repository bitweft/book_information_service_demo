package com.book_information.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_information")
public class BookInformation implements Comparable<BookInformation> {
    @Id
    private String bookId;
    private String title;
    private String author;
    private Long publishDate;

    public BookInformation() {
    }

    public BookInformation(String bookId, String title, String author, long publishDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    @Override
    public int compareTo(BookInformation other) {
        return Long.compare(publishDate, other.publishDate);
    }
}
