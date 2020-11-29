Feature: As an user, I want to be able to search books.

    Scenario Outline: Search bar functions when no books have been added.
      Given application has opened
      When "clean" is entered as the search term
      Then the book list is empty

    Scenario Outline: The search bar filters the books list correctly
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      When "i" is entered as the search term
      Then the book list contains 1 books
      Then the first element of the book list is a book with "Clean Architecture" as name and "Robert Martin" as author and 400 as page count
      When "code" is entered as the search term
      Then the book list contains 2 books
      Then the first element of the book list is a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the second element of the book list is a book with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      When "ther" is entered as the search term
      Then the book list contains 0 books
      When the search field is cleared
      Then the book list contains 3 books

    Scenario Outline: Book deletion works while the book list is filtered
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      When "code" is entered as the search term
      Then the book list contains 2 books
      Then the first element of the book list is a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the second element of the book list is a book with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      When the delete button next to a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count is pressed
      When "OK" is clicked in the confirmation dialog
      Then the book list contains 1 books
      When the search field is cleared
      Then the book list contains 2 books
      Then the first element of the book list is a book with "Clean Architecture" as name and "Robert Martin" as author and 400 as page count
      Then the second element of the book list is a book with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count

    Scenario Outline: Book can be added while the book list is filtered
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When "code" is entered as the search term
      Then the book list contains 1 books
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      Then the book list contains 2 books
      When a book with "Pragmatic Programmer" as name and "Andy Hunt ja Dave Thomas" as author and "320" as page count is added
      Then the book list contains 2 books
      Then the first element of the book list is a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the second element of the book list is a book with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      When the search field is cleared
      When "Pragmatic Programmer" is entered as the search term
      Then the book list contains 1 books
      Then the first element of the book list is a book with "Pragmatic Programmer" as name and "Andy Hunt ja Dave Thomas" as author and 320 as page count