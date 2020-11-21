Feature: As a user I want to be able to add a book to the application
  which has a name, author and a page_number


  Scenario Outline: Book has or has not name
    Given book is named "<name>"
    When I ask whether the name is filled in or not
    Then I should be told "<answer>"

    Examples:
      | name           | answer |
      | Correct_Name   | Yes    |
      | abababa        | No     |

  Scenario Outline: Book has or has not author
    Given book has author "<author>"
    When I ask whether the author is filled in or not
    Then I should be told "<answer>"

    Examples:
      | author           | answer |
      | Correct_Author   | Yes    |
      | abababa          | No     |

  Scenario Outline: Book has or has not pages
    Given book has pages "<pagenumber>"
    When I ask whether the pagenumber is filled in or not
    Then I should be told "<answer>"

    Examples:
      | pagenumber       | answer |
      | Correct_Pages    | Yes    |
      | abababa          | No     |

  Scenario Outline: Book has name author and pages
    Given book has name "<name>" author "<author>" and pages "<pagenumber>"
    When I ask whether they are filled in or not
    Then I should be told "<answer>"

    Examples:
      | name           | author          | pagenumber       | answer |
      | Correct_Name   | Correct_Author  | Correct_Pages    | Yes    |
      | Correct_Name   | Correct_Author  | abababa          | No     |
      | Correct_Name   | abababa         | Correct_Pages    | No     |
      | Correct_Name   | abababa         | abababa          | No     |
      | abababa        | Correct_Author  | Correct_Pages    | No     |
      | abababa        | Correct_Author  | abababa          | No     |
      | abababa        | abababa         | Correct_Pages    | No     |
      | abababa        | abababa         | abababa          | No     |

