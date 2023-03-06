package com.sexydari.betriush;

public class Smash {
    private final String smash;
    private final int smashCounter;
    private final boolean smashable;

    public Smash(String smash, int smashCounter, boolean smashable) {
        this.smash = smash;
        this.smashCounter = smashCounter;
        this.smashable = smashable;
    }

    public String getSmash() {
        return smash;
    }

    public int getSmashCounter() {
        return smashCounter;
    }

    public boolean getSmashable() {
        return smashable;
    }

    @Override
    public String toString() {
        return "Smash{" +
                "smash='" + smash + '\'' +
                ", smashCounter=" + smashCounter +
                ", smashable=" + smashable +
                '}';
    }
}
