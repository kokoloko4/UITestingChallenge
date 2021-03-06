package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.SearchPage;

import static org.junit.Assert.assertTrue;

public class FilterSteps {

    private static HomePage homePage = new HomePage();
    private static SearchPage searchPage = new SearchPage();

    private int maxPrice;
    private int minPrice;

    @And("^I search for \"([^\"]*)\"$")
    public void iSearchForWord(String word){
        homePage.findProduct(word);
    }

    @When("^I apply the filter for price between \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iApplyTheFilterForPrice(String minimum, String maximum){
        maxPrice = Integer.parseInt(maximum.replace(".", ""));
        minPrice = Integer.parseInt(minimum.replace(".", ""));
        searchPage.filterByPrice(minimum, maximum);
    }

    @Then("^I should see just products with prices in this range$")
    public void iShouldSeeProductsInRange(){
        assertTrue("A product is not in range",
                searchPage.arePricesCorrect(minPrice, maxPrice));
    }
}
