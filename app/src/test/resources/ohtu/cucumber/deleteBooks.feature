Feature: As an user, I want to be able to delete books.

  Scenario Outline: Added books can be deleted by clicking the button next to the book.
    Given application has opened
    When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
    When the delete button next to a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count is pressed
    Then the book list is empty