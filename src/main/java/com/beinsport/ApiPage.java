package com.beinsport;

import java.util.ArrayList;
import java.util.Random;

public class ApiPage {

    private ArrayList<ArrayList<String>> creditCards = new ArrayList<ArrayList<String>>();

    public ApiPage(){
        makeCreditCard();
    }

    public ArrayList<String> getCreditCard() {
        int randomCard = getRandomNumberInRange(0,1);

        return creditCards.get(0);
    }

    protected void makeCreditCard(){

        //CreditCard informations were given from the link
        //https://developers.bluesnap.com/docs/test-credit-cards
        ArrayList<String> nonValidCard = new ArrayList<String>();
        ArrayList<String> card1 = new ArrayList<String>();
        ArrayList<String> card2 = new ArrayList<String>();

        nonValidCard.add("nonvalid Test Card");
        nonValidCard.add("1234567890123456");
        nonValidCard.add("02");
        nonValidCard.add("2023");
        nonValidCard.add("111");

        card1.add("Test Card");
        card1.add("4263982640269299");
        card1.add("02");
        card1.add("2023");
        card1.add("837");

        card2.add("Test Card");
        card2.add("4263982640269299");
        card2.add("04");
        card2.add("2023");
        card2.add("738");

        creditCards.add(nonValidCard);
        creditCards.add(card1);
        creditCards.add(card2);

    }

    public int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}
