Feature: As a user I want to be able to add a book to the application
  which has a name, author and a page number

    Scenario Outline: Article can be added when all necessary information is filled in
      Given application has opened
      When  add article button is clicked
      When  form is filled with "Nachopelti resepti" as title and "www.google.com" as hyperlink
      When  form is submitted
      Then  the bookmark list contains 1 bookmarks
      Then  bookmark list contains an article with "Nachopelti resepti" as title and "www.google.com" as hyperlink

    Scenario Outline: Article cannot be added without a heading
      Given application has opened
      When add article button is clicked
      When form is filled with "" as title and "www.google.com" as hyperlink
      When form is submitted
      Then view contains error message "Täytä kaikki tiedot."

    Scenario Outline: Article cannot be added without a hyperlink
      Given application has opened
      When add article button is clicked
      When form is filled with "Nachopelti resepti" as title and "" as hyperlink
      When form is submitted
      Then view contains error message "Täytä kaikki tiedot."
