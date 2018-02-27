package com.ebichel;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlockchain {

    private List<Block> chain;

    public SimpleBlockchain() {
        this.chain = new ArrayList();
        this.chain.add(this.createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, "28/02/2018", "Genesis block", "0");
    }

    private Block getLatestBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock.setPreviousHash(this.getLatestBlock().getHash());
        newBlock.setHash(newBlock.calculateHash());
        this.chain.add(newBlock);
    }

    public boolean isChainValid() {

        final int size = this.chain.size();

        for(int i = 1; i < size; i++) {
            final Block currBlock = this.chain.get(i);
            final Block prevBlock = this.chain.get(i - 1);

            if(currBlock.getHash() != currBlock.calculateHash() ||
                    currBlock.getPreviousHash() != prevBlock.getHash()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {

        final int size = this.chain.size();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < size; i++) {
            sb.append(this.chain.get(i).toString()).append("\n\r");
        }

        return sb.toString();
    }
}
