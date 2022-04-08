Feature: Book Search
    Allow a customer to find his books quickly. The library must
    offer multiple ways to search for a book
Background: Books
    Given these books
    | title | author | published | Category |
    | Hunger Games | Suzanne Collins | 14 September 2008 | Distopia |
    | Daisy Jones and The Six | Taylor Jenkins Reid | 5 March 2019 | Young Adult |
    | The Seven Husbands of Evelyn Hugo | Taylor Jenkins Reid | 13 June 2017 | Young Adult |
    | Misery | Stephen King | 8 June 1987 | Horror |
Scenario: Search books by author
    When a customer searches for books written by 'Taylor Jenkins Reid'
    Then 2 books should be found
    And book1 should have the title 'Daisy Jones and The Six'
    And book2 should have the title 'The Seven Husbands of Evelyn Hugo'
Scenario: Search books by publication date
    When a customer searches for books written before 2010
    Then 2 books should be found
    And book1 should have the title 'Hunger Games'
    And book2 should have the title 'Misery'
Scenario: Search books by category
    When a customer searches for books of the 'Horror' category
    Then 1 books should be found
    And book1 should have the title 'Misery'