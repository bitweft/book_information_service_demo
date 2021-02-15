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
    private String imageUrl;

    public BookInformation() {
    }

    public BookInformation(String bookId, String title, String author, String imageUrl, long publishDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
    }

    @Override
    public int compareTo(BookInformation other) {
        return Long.compare(publishDate, other.publishDate);
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getPublishDate() {
        return publishDate;
    }
}
