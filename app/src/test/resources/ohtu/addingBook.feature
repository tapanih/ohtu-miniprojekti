Feature: As a user I want to be able to add a book to the application
  which has a name, author and a page_number

  Scenario Outline: Book has name author and pages
    Given book has name "<name>" author "<author>" and pages <pagenumber>
    When I ask whether they are filled in or not
    Then I should be told "<answer>"

    Examples:
      | name           | author          | pagenumber | answer |
      | Correct_Name   | Correct_Author  | 1          | Yes    |
      | Correct_Name   | Correct_Author  | 0          | No     |
      | Correct_Name   | Correct_Author  | -28        | No     |
      | Correct_Name   | not found       | 1000       | No     |
      | Correct_Name   | not found       | 0          | No     |
      | not found      | Correct_Author  | 100        | No     |
      | not found      | Correct_Author  | 0          | No     |
      | not found      | not found       | 1          | No     |
      | not found      | not found       | 0          | No     |

    Scenario Outline: Book can be added
      Given application has opened
      When add book button is clicked
      When form is filled with "Clean Code" as name and "Robert Martin" as author and "400" as page count
      Then book list contains a book with "Clean Code" as name and "Robert Martin" as author and 400 as page count
