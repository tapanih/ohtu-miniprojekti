Feature: As an user, I want to be able to search books by tags.

  Scenario Outline: The search bar filters the bookmarks list by tags correctly
    Given application has opened
    When a book with "Harry Potter and the Philosopher's Stone" as name and "J. K. Rowling" as author and "400" as page count is added
    When a book with "Harry Potter and the Chamber of Secrets" as name and "J. K. Rowling" as author and "400" as page count is added
    When the details button next to a book with "Harry Potter and the Chamber of Secrets" as title and "J. K. Rowling" as author and 400 as page count is clicked
    When "good read" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When the details button next to a book with "Harry Potter and the Philosopher's Stone" as title and "J. K. Rowling" as author and 400 as page count is clicked
    When "good read" is entered as a new tag to be added
    When button to add the entered tag is clicked
    When the return button is pressed
    When search by tags is clicked
    When "good rea" is entered as the search term
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
