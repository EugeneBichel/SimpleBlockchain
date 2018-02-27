package com.ebichel;

public class Main {

    public static void main(String[] args) {
        SimpleBlockchain simpleBlockchain = new SimpleBlockchain();
        simpleBlockchain.addBlock(new Block(1, "26/02/2018", "{amount: 100}"));
        simpleBlockchain.addBlock(new Block(2, "26/02/2018", "{amount: 120}"));

        System.out.println(simpleBlockchain.toString());
    }
}
