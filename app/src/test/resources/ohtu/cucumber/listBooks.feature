Feature: As an user, I want to list added books.

    Scenario Outline: No books are listed when none are added when the application is opened.
      Given application has opened
      Then the book list is empty

    Scenario Outline: Added books are listed
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      Then the book list contains 3 books
