package io.github.izycorp.codapi.components;

public enum TelescopeOpus implements AbstractOpus {

    MW2("mw2"),
    WARZONE2("wz2"),
    MW3("jup"),
    MOBILE("mgl");

    /**
     * Identifier is the valid string value of an opus in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    TelescopeOpus(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
