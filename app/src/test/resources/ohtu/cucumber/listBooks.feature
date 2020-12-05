Feature: As an user, I want to list added bookmarks.

    Scenario Outline: No bookmarks are listed when none are added.
      Given application has opened
      Then the bookmark list is empty

    Scenario Outline: Added bookmarks are listed properly
      Given application has opened
      When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "Clean Architecture" as name and "Robert Martin" as author and "400" as page count is added
      When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
      When an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink is added
      Then the bookmark list contains 4 bookmarks
      Then the element number 1 of the bookmark list is a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count
      Then the element number 2 of the bookmark list is a bookmark with "Clean Architecture" as name and "Robert Martin" as author and 400 as page count
      Then the element number 3 of the bookmark list is a bookmark with "The Clean Coder" as name and "Robert Martin" as author and 400 as page count
      Then the element number 4 of the bookmark list is a bookmark with "Nachopelti resepti" as title and "www.google.com" as hyperlink
