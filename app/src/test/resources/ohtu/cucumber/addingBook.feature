Feature: As a user I want to be able to add a book to the application
  which has a name, author and a page number

    Scenario Outline: Book can be added when all necessary information is filled in
      Given application has opened
      When  add book button is clicked
      When  form is filled with "Title" as name and "Author" as author and "444" as page count
      Then  book list contains a book with "Title" as name and "Author" as author and 400 as page count
    
    Scenario Outline: Book cannot be added without a heading
      Given application has opened
      When  add book button is clicked
      When  form is filled with "" as name and "J. K. Rolling" as author and "400" as page count
      Then  view contains error message "Täytä kaikki tiedot."

    Scenario Outline: Book cannot be added without an author
      Given application has opened
      When  add book button is clicked
      When  form is filled with "Harry Potta" as name and "" as author and "400" as page count
      Then  view contains error message "Täytä kaikki tiedot."

    Scenario Outline: Book cannot be added without a page count
      Given application has opened
      When  add book button is clicked
      When  form is filled with "Harry Potta" as name and "J. K. Rolling" as author and "" as page count
      Then  view contains error message "Täytä kaikki tiedot."

    Scenario Outline: Book cannot be added if all information is blank
      Given application has opened
      When  add book button is clicked
      When  form is filled with "" as name and "" as author and "" as page count
      Then  view contains error message "Täytä kaikki tiedot."