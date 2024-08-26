package com.example.java;

class Card2 {
    static final int KIND_MAX = 4;
    static final int NUM_MAX = 13;

    static final int SPADE = 4;
    static final int DIAMOND = 3;
    static final int HEART = 2;
    static final int CLOVER = 1;

    int kind;
    int number;

    public Card2() {
        this(SPADE, 1);
    }

    public Card2(int kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    @Override
    public String toString() {
        String[] kinds = { "", "CLOVER", "HEART", "DIAMOND", "SPADE" };
        String numbers = "0123456789XJQK";

        return "kind : " + kinds[kind] + ", number : " + numbers.charAt(number);
    }
}

class Deck {
    final int CARD_NUM = 52;
    Card2[] cards = new Card2[CARD_NUM];

    public Deck() {
        int i = 0;

        for ( int k = Card2.KIND_MAX; k > 0; k-- ) {
            for ( int n = 0; n < Card2.NUM_MAX; n++ ) {
                cards[i++] = new Card2(k, n + 1);
            }
        }
    }

    public Card2 pick(int index) {
        return cards[index];
    }

    public Card2 pick() {
        int index = (int)(Math.random() * CARD_NUM); // 덱에서 카드 하나를 선택한다.
        return pick(index);
    }

    public void shuffle() {
        for ( int i = 0; i < cards.length; i++ ) {
            int index = (int)(Math.random() * CARD_NUM);

            Card2 temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
    }
}

public class G_card_play {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Card2 card = deck.pick(0);
        System.out.println(card);

        deck.shuffle();
        card = deck.pick(0);
        System.out.println(card);
    }
}
