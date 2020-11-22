package ohtu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

class IsEverythingCorrect {
    static String isEverythingCorrect(String name, String author, Integer pages) {
        return ("Correct_Name").equals(name) && ("Correct_Author").equals(author) && (pages>0) ? "Yes" : "No";
    }
}


public class StepDefinitions {
    private String name;
    private String author;
    private int pages;
    private String actualAnswer;

    @Given("book has name {string} author {string} and pages {int}")
    public void book_has_name_author_and_pages(String name, String author, int pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }

    @When("I ask whether they are filled in or not")
    public void i_ask_whether_they_are_filled_in_or_not() {
        if (this.name.isEmpty()){
            actualAnswer = "not found";
        } else if (this.author.isEmpty()){
            actualAnswer = "not found";
        } else if (this.pages <= 0){
            actualAnswer = "No";
        } else {
            actualAnswer = IsEverythingCorrect.isEverythingCorrect(name, author, pages);
        }
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
