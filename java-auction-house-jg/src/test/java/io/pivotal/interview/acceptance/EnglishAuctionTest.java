package io.pivotal.interview.acceptance;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

public class EnglishAuctionTest extends CliTestBase {

    @Test
    public void shouldPromptForAuctionTypeAndStartingPrice() throws Exception {
        assertPrompt("Seller: enter the auction type");
        enterText("English");
        assertPrompt("Seller: enter the starting price");
        enterText(100);
        assertPrompt("Notice: created an auction of type English with starting price 100");
    }

    @Test
    public void shouldPromptForBidders() throws Exception {
        createAuction("English", 10);
        assertPrompt("Bidders: enter your names (blank line to finish)");
        enterText("Agatha");
        enterText("Bertram");
        enterText("Celia");
        enterText("");
        assertPrompt("Notice: the bidders are Agatha, Bertram, Celia");
    }

    @Test
    public void shouldAskEachBidderMultipleTimes() throws Exception {
        createAuction("English", 10);
        registerBidders("Agatha", "Bertram", "Celia");

        assertAndBid("Agatha", 10);
        assertAndBid("Bertram", 11);
        assertAndBid("Celia", 12);

        assertAndBid("Agatha", 13);
        assertAndBid("Bertram", 14);
        assertAndBid("Celia", 15);
    }

    @Test
    public void shouldShowCorrectMinimumPrice() throws IOException {
        createAuction("English", 10);
        registerBidders("Agatha", "Bertram", "Celia");

        assertMinPriceAndBid("Agatha", 10, 10);
        assertMinPriceAndBid("Bertram", 11, 20);
        assertMinPriceAndBid("Celia", 21, 25);

        assertMinPriceAndBid("Agatha", 26, 30);
        assertMinPriceAndBid("Bertram", 31, 31);
        assertMinPriceAndBid("Celia", 32, 100);
    }

    @Test
    public void auctionFinishesWhenAllBiddersPass() throws IOException {
        createAuction("English", 10);
        registerBidders("Agatha", "Bertram", "Celia");

        assertMinPriceAndBid("Agatha", 10, 10);
        assertMinPriceAndBid("Bertram", 11, 20);
        assertMinPriceAndBid("Celia", 21, 25);

        assertAndPass("Agatha");
        assertAndPass("Bertram");
        assertAndPass("Celia");

        assertWinner("Celia", 25);
    }

    @Test
    public void runsAnotherRoundIfNotAllBiddersPass() throws IOException {
        createAuction("English", 10);
        registerBidders("Agatha", "Bertram", "Celia");

        assertMinPriceAndBid("Agatha", 10, 10);
        assertMinPriceAndBid("Bertram", 11, 20);
        assertMinPriceAndBid("Celia", 21, 25);

        assertMinPriceAndBid("Agatha", 26, 26);
        assertAndPass("Bertram");
        assertMinPriceAndBid("Celia", 27, 30);

        assertAndPass("Agatha");
        assertAndPass("Bertram");
        assertAndPass("Celia");

        assertWinner("Celia", 30);
    }
}
