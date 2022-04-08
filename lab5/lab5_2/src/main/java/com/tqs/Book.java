package com.tqs;

import java.util.Date;

public class Book {
    private final String title;
    private final String author;
    private final Date published;
    private final String category;

    public Book(String title, String author, Date published, String category) {
        this.title = title;
        this.author = author;
        this.published = published;
        this.category = category;
    }


    public String getTitle() {
        return this.title;
    }


    public String getAuthor() {
        return this.author;
    }


    public Date getPublished() {
        return this.published;
    }


    public String getCategory() {
        return this.category;
    }


    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", published='" + getPublished() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }


}

    
