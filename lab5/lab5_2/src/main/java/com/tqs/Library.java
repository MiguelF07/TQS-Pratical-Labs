package com.tqs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library {
    private final List<Book> library;


    public Library() {
        this.library = new ArrayList<>();
    }

    public void addBook(final Book book) {
        this.library.add(book);
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> searched = new ArrayList<>();
        for(Book b : library) {
            if(b.getAuthor().equals(author)) {
                searched.add(b);
            }
        }
        return searched;
    }

    public List<Book> findBooksByDate(final Date from, final Date to) {
        List<Book> searched = new ArrayList<>();
        for(Book b : library) {
            if(b.getPublished().after(from) && b.getPublished().before(to)) {
                searched.add(b);
            }
        }
        return searched;
    }

    public List<Book> findBooksByCategory(String category) {
        List<Book> searched = new ArrayList<>();
        for(Book b : library) {
            if(b.getCategory().equals(category)) {
                searched.add(b);
            }
        }
        return searched;
    }

}
