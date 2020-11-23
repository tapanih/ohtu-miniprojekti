Feature: As a user I want to be able to add a book to the application
  which has a name, author and a page number

    Scenario Outline: Book can be added
      Given application has opened
      When add book button is clicked
      When form is filled with "Clean Code" as name and "Robert Martin" as author and "400" as page count
      Then book list contains a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      
    Scenario: Book can not be added to the application
      Given book is not initialised with name author and pages
      When the book is added into the application
      Then it should not save the book into the service

