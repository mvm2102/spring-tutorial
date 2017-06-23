package com.baeldung.injectiontypes.constructorbased.model;

public class Engine {
    private String type;
    private int volume;

    public Engine() {
    }

    public Engine(String type, int volume) {
        this.setType(type);
        this.setVolume(volume);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return this.volume;
    }

    @Override
    public String toString() {
        return String.format("Engine type: %s volume: %d L", this.type, this.volume);
    }
}