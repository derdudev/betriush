package com.sexydari.betriush;

public class InitDataResponse {
    private final boolean success;
    private final boolean addedNewEntries;

    public InitDataResponse(boolean success, boolean addedNewEntries) {
        this.success = success;
        this.addedNewEntries = addedNewEntries;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isAddedNewEntries() {
        return addedNewEntries;
    }
}
