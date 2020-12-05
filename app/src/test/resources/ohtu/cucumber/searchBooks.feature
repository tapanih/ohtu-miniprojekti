Feature: As an user, I want to be able to search bookmarks.

    Scenario Outline: Search bar functions when no bookmarks have been added.
      Given application has opened
      When "clean" is entered as the search term
      Then the bookmark list is empty

    Scenario Outline: The search bar filters the bookmarks list correctly
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      When "i" is entered as the search term
      Then the bookmark list contains 1 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Clean Architecture" as name and "Robert Martin" as author and 400 as page count
      When "code" is entered as the search term
      Then the bookmark list contains 2 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the element number 2 of the bookmark list is a bookmark with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      When "ther" is entered as the search term
      Then the bookmark list contains 0 bookmarks
      When the search field is cleared
      Then the bookmark list contains 3 bookmarks

    Scenario Outline: Book deletion works while the bookmark list is filtered
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      When "code" is entered as the search term
      Then the bookmark list contains 2 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the element number 2 of the bookmark list is a bookmark with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      When the delete button next to a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count is pressed
      When "OK" is clicked in the confirmation dialog
      Then the bookmark list contains 1 bookmarks
      When the search field is cleared
      Then the bookmark list contains 2 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Clean Architecture" as name and "Robert Martin" as author and 400 as page count
      Then the element number 2 of the bookmark list is a bookmark with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count

    Scenario Outline: Book can be added while the bookmark list is filtered
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When "code" is entered as the search term
      Then the bookmark list contains 1 bookmarks
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      Then the bookmark list contains 2 bookmarks
      When a book with "Pragmatic Programmer" as name and "Andy Hunt ja Dave Thomas" as author and "320" as page count is added
      Then the bookmark list contains 2 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the element number 2 of the bookmark list is a bookmark with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      When the search field is cleared
      When "Pragmatic Programmer" is entered as the search term
      Then the bookmark list contains 1 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Pragmatic Programmer" as name and "Andy Hunt ja Dave Thomas" as author and 320 as page count