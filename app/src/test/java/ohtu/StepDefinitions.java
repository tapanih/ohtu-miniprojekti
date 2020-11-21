package ohtu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

class IsItNamedCorrectly {
    static String isItNamedCorrectly(String name) {
        return "Correct_Name".equals(name) ? "Yes" : "No";
    }
}

class IsItAuthoredCorrectly {
    static String isItAuthoredCorrectly(String author) {
        return "Correct_Author".equals(author) ? "Yes" : "No";
    }
}

class IsItPagedCorrectly {
    static String isItPagedCorrectly(String pages) {
        return "Correct_Pages".equals(pages) ? "Yes" : "No";
    }
}

class IsEverythingCorrect {
    static String isEverythingCorrect(String name, String author, String pages) {
        return ("Correct_Name").equals(name) && ("Correct_Author").equals(author) && ("Correct_Pages").equals(pages) ? "Yes" : "No";
    }
}


public class StepDefinitions {
    private String name;
    private String author;
    private String pages;
    private String actualAnswer;

    @Given("book is named {string}")
    public void book_is_named(String name) {
        this.name = name;
    }

    @When("I ask whether the name is filled in or not")
    public void i_ask_whether_the_name_is_filled_in_or_not() {
        if (this.name.isEmpty()){
            actualAnswer = "abababa";
        } else {
            actualAnswer = IsItNamedCorrectly.isItNamedCorrectly(name);
        }
    }

    @Given("book has author {string}")
    public void book_has_author(String author) {
        this.author = author;
    }

    @When("I ask whether the author is filled in or not")
    public void i_ask_whether_the_author_is_filled_in_or_not() {
        if (this.author.isEmpty()){
            actualAnswer = "abababa";
        } else {
            actualAnswer = IsItAuthoredCorrectly.isItAuthoredCorrectly(author);
        }
    }

    @Given("book has pages {string}")
    public void book_has_pages(String pages) {
        this.pages = pages;
    }

    @When("I ask whether the pagenumber is filled in or not")
    public void i_ask_whether_the_pagenumber_is_filled_in_or_not() {
        if (this.pages.isEmpty()){
            actualAnswer = "abababa";
        } else {
            actualAnswer = IsItPagedCorrectly.isItPagedCorrectly(pages);
        }
    }

    @Given("book has name {string} author {string} and pages {string}")
    public void book_has_name_author_and_pages(String name, String author, String pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }

    @When("I ask whether they are filled in or not")
    public void i_ask_whether_they_are_filled_in_or_not() {
        if (this.name.isEmpty()){
            actualAnswer = "abababa";
        } else if (this.author.isEmpty()){
            actualAnswer = "abababa";
        } else if (this.pages.isEmpty()){
            actualAnswer = "abababa";
        } else {
            actualAnswer = IsEverythingCorrect.isEverythingCorrect(name, author, pages);
        }
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}