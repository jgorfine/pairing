package io.pivotal.interview;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cli implements Runnable {

    private final BufferedReader input;
    private final BufferedWriter output;

    public Cli(BufferedReader input, BufferedWriter output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        try {
            write("Seller: enter the auction type");
            String type = readString();
            write("Seller: enter the starting price");
            int startingPrice = readInt();
            write("Notice: created an auction of type " + type + " with starting price " + startingPrice);

            write("Bidders: enter your names (blank line to finish)");
            List<String> bidders = new ArrayList<>();
            String bidderOrBlank;
            while (!(bidderOrBlank = readString()).isEmpty()) {
                bidders.add(bidderOrBlank);
            }
            write("Notice: the bidders are " + String.join(", ", bidders));

            Map<String, Bid> bids = new HashMap<>();

            if (type.equals("English")) {
                do {
                    askForBids(startingPrice, bidders, bids);
                } while (anyBidsPlaced(bids));
            } else {
                // blind auction
//                askForBlindBids(startingPrice, bidders, bids);

            }

            String winner = null;
            int winningBid = -1;
            for (Map.Entry<String, Bid> bidderAndBid : bids.entrySet()) {
                String bidder = bidderAndBid.getKey();
                Bid bid = bidderAndBid.getValue();
                if (bid.value > winningBid) {
                    winner = bidder;
                    winningBid = bid.value;
                }
            }

            write("Notice: " + winner + " has won the auction at a price of " + winningBid);
        } catch (IOException e) {
            if (!e.getMessage().equals("Stream closed")) {
                e.printStackTrace();
            }
        }
    }

    private void askForBids(int startingPrice, List<String> bidders, Map<String, Bid> bids) throws IOException {
        for (String bidder : bidders) {
            int minimumPrice = bids.isEmpty() ? startingPrice : getHighestBid(bids) + 1;
            write("Bidder " + bidder + ": the minimum price is " + minimumPrice + "; enter your bid");
            int bidValue = readInt();

            Bid newBid;
            if (bidValue != -1) {
                newBid = new Bid(bidValue, false);
            } else {
                newBid = new Bid(bids.get(bidder).value, true);
            }

            bids.put(bidder, newBid);
        }
    }

    private boolean anyBidsPlaced(Map<String, Bid> bids) {
        for (Map.Entry<String, Bid> bidderAndBid : bids.entrySet()) {
            boolean didPass = bidderAndBid.getValue().didPass;
            if (!didPass) {
                return true;
            }
        }
        return false;
    }

    private Integer getHighestBid(Map<String, Bid> bids) {
        int highestBid = -1;
        for (Map.Entry<String, Bid> bidderAndBid : bids.entrySet()) {
            int bid = bidderAndBid.getValue().value;
            if (bid > highestBid) {
                highestBid = bid;
            }
        }
        return highestBid;
    }



    private void write(String prompt) throws IOException {
        output.write(prompt);
        output.write('\n');
        output.flush();
    }

    private String readString() throws IOException {
        return input.readLine();
    }

    private int readInt() throws IOException {
        int value;
        try {
            value = Integer.parseInt(input.readLine());
        } catch(NumberFormatException error) {
            value = -1;
        }
        return value;
    }

    public static void main(String... args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        new Cli(input, output).run();
    }

    private class Bid {
        public int value;
        public boolean didPass;

        public Bid(int value, boolean didPass) {
            this.value = value;
            this.didPass = didPass;
        }
    }

}
