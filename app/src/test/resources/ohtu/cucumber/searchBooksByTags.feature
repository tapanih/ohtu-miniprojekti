Feature: As an user, I want to be able to search books by tags.

  Scenario Outline: Books not found when filtered by tag
    Given application has opened
    When search by tags is clicked
    When "zzzzzzzzzzzzzzzz" is entered as the search term
    Then the bookmark list contains 0 bookmarks

  Scenario Outline: Book can be added while the bookmark list is filtered by tag
    Given application has opened
    When a book with "abababa" as name and "abababa" as author and "10000" as page count is added
    When a book with "ababababa" as name and "ababababa" as author and "9000" as page count is added
    When search by tags is clicked
    When "bababa" is entered as the search term
    Then the bookmark list contains 0 bookmarks
    When a book with "bababa" as name and "bababa" as author and "400" as page count is added
    Then the bookmark list contains 0 bookmarks
    When search by books is clicked
    Then the bookmark list contains 3 bookmarks

  Scenario Outline: The search bar filters the bookmarks list by tags correctly
    Given application has opened
    When a book with "The Clean Coder" as name and "Robert Martin" as author and "400" as page count is added
    When a book with "Sherlock Holmes" as name and "Arthur Conan Doyle" as author and "400" as page count is added
    When an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink is added
    When the details button next to a book with "The Clean Coder" as title and "Robert Martin" as author and 400 as page count is clicked
    When "good read" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When the details button next to a book with "Sherlock Holmes" as title and "Arthur Conan Doyle" as author and 400 as page count is clicked
    When "good read" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When search by tags is clicked
    When "good" is entered as the search term
    Then the bookmark list contains 2 bookmarks

  Scenario Outline: The search bar filters the bookmarks list correctly even when books and articles have the same tag
    Given application has opened
    When a book with "abra" as name and "kadabra" as author and "101" as page count is added
    When a book with "saippuakivi" as name and "kauppias" as author and "323" as page count is added
    When an article with "Nachopelti resepti 1" as title and "www.google.com" as hyperlink is added
    When an article with "Nachopelti resepti 2" as title and "www.google.com" as hyperlink is added
    When the details button next to an article with "Nachopelti resepti 1" as title and "www.google.com" as hyperlink is clicked
    When "resepti" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When the details button next to an article with "Nachopelti resepti 2" as title and "www.google.com" as hyperlink is clicked
    When "resepti" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When the details button next to a book with "abra" as title and "kadabra" as author and 101 as page count is clicked
    When "resepti" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When search by tags is clicked
    When "rese" is entered as the search term
    Then the bookmark list contains 3 bookmarks
