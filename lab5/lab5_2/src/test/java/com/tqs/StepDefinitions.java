package com.tqs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private List<Book> books;
    private Library lib = new Library();

    @Given("these books")
    public void these_books(DataTable table) throws ParseException {
        Book book1 = new Book("Hunger Games", "Suzanne Collins", new SimpleDateFormat("yyyy/MM/dd").parse("2008/09/14"),
                "Distopia");
        Book book2 = new Book("Daisy Jones and The Six", "Taylor Jenkins Reid",
                new SimpleDateFormat("yyyy/MM/dd").parse("2019/03/05"), "Young Adult");
        Book book3 = new Book("The Seven Husbands of Evelyn Hugo", "Taylor Jenkins Reid",
                new SimpleDateFormat("yyyy/MM/dd").parse("2017/06/13"), "Young Adult");
        Book book4 = new Book("Misery", "Stephen King", new SimpleDateFormat("yyyy/MM/dd").parse("1987/06/08"),
                "Horror");
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addBook(book4);
    }

    @When("a customer searches for books written by {string}")
    public void a_customer_searches_for_books_written_by(String author) {
        books = lib.findBooksByAuthor(author);
    }

    @Then("{int} books should be found")
    public void books_should_be_found(Integer num) {
        assertEquals(num, books.size());
    }

    @Then("book1 should have the title {string}")
    public void book1_should_have_the_title(String title) {
        assertEquals(title, books.get(0).getTitle());
    }

    @Then("book2 should have the title {string}")
    public void book2_should_have_the_title(String title) {
        assertEquals(title, books.get(1).getTitle());
    }

    // @Then("{int} book should be found")
    // public void books_should_be_found(Integer num) {
    //     assertEquals(num, books.size());
    // }

    // @Then("book1 should have the title {string}")
    // public void book1_should_have_the_title(String title) {
    //     assertEquals(title, books.get(0).getTitle());
    // }

    // @Then("book2 should have the title {string}")
    // public void book2_should_have_the_title(String title) {
    //     assertEquals(title, books.get(1).getTitle());
    // }

    @When("a customer searches for books written before {int}")
    public void a_customer_searches_for_books_written_before(Integer num) throws ParseException {
        books = lib.findBooksByDate(new SimpleDateFormat("yyyy/MM/dd").parse("1000/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse(num+"/01/01"));
    }

    @When("a customer searches for books of the {string} category")
    public void a_customer_searches_for_books_of_the_category(String category) {
        books = lib.findBooksByCategory(category);
    }

    // @Then("{int} books should be found")
    // public void books_should_be_found(Integer num) {
    //     assertEquals(num, books.size());
    // }

    // @Then("book1 should have the title {string}")
    // public void book1_should_have_the_title(String title) {
    //     assertEquals(title, books.get(0).getTitle());
    // }

    // @Then("book2 should have the title {string}")
    // public void book2_should_have_the_title(String title) {
    //     assertEquals(title, books.get(1).getTitle());
    // }

}