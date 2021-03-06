Feature: As an user, I want to be able add and remove tags to books or articles.

  Scenario Outline: Tags can be added to and deleted from books.
    Given application has opened
    When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
    When an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink is added
    When the details button next to an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink is clicked
    When "resepti" is entered as a new tag to be added
    When button to add the entered tag is clicked
    Then the tag list contains 1 tags
    When the delete button next to a tag named "resepti" is clicked
    When "OK" is clicked in the confirmation dialog
    Then the tag list contains 0 tags


  Scenario Outline: Tags can be added to and deleted from articles.
    Given application has opened
    When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
    When an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink is added
    When the details button next to a book with "The Clean Coder" as title and "Robert Martin" as author and 400 as page count is clicked
    When "ohjelmointi" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When "Java" is entered as a new tag to be added
    When button to add the entered tag is clicked
    Then the tag list contains 2 tags
    When the delete button next to a tag named "ohjelmointi" is clicked
    When "OK" is clicked in the confirmation dialog
    Then the tag list contains 1 tags

  Scenario Outline: Same tags cannot be added twice to a same bookmark.
    Given application has opened
    When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
    When the details button next to a book with "The Clean Coder" as title and "Robert Martin" as author and 400 as page count is clicked
    When "ohjelmointi" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When "ohjelmointi" is entered as a new tag to be added
    When button to add the entered tag is clicked
    Then the tag list contains 1 tags