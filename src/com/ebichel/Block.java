package com.ebichel;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Block {

    private int index;
    private String timestamp;
    private String data;
    private String previousHash;
    private String hash;

    public Block(int index, String timestamp, String data, String previousHash ) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
    }

    public Block(int index, String timestamp, String data ) {
        this(index, timestamp, data, "");
    }

    public String calculateHash() {
        final String text = this.index + this.previousHash + this.timestamp + this.data;

        return getSha256(text);
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public void setPreviousHash(String previousHash) {
         this.previousHash = previousHash;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return this.index + " " + this.timestamp + " " + this.data + " " + this.hash + " " + this.previousHash;
    }

    private String getSha256(String text) {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(hash);
    }
}
