Feature: As an user, I want to be able to delete bookmarks.

  Scenario Outline: Added books can be deleted by clicking the button next to the book.
    Given application has opened
    When a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
    When the delete button next to a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count is pressed
    When "OK" is clicked in the confirmation dialog
    Then the bookmark list is empty

  Scenario Outline: Added articles can be deleted by clicking the button next to the book.
    Given application has opened
    When an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink is added
    When the delete button next to a bookmark with "Nachopelti resepti" as title and "www.google.com" as hyperlink is pressed
    When "OK" is clicked in the confirmation dialog
    Then the bookmark list is empty

  Scenario Outline: Deletion can be cancelled in the confirmation dialog
    Given application has opened
    When  a book with "Clean Code" as name and "Robert Martin" as author and "400" as page count is added
    When  the delete button next to a bookmark with "Clean Code" as name and "Robert Martin" as author and 400 as page count is pressed
    When  "Cancel" is clicked in the confirmation dialog
    Then  the bookmark list contains 1 bookmarks