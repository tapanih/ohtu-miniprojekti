Feature: As a user I want to be able to add a book to the application which has a name, author and a page_number

  Scenario: User can add a book to the system
    Given a name, an author and a page number
    When a book is given these three values and added
    Then the service should be updated with that book
