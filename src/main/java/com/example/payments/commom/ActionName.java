package com.example.payments.commom;

public enum ActionName {
    NULL_CHECK("checkNullResource"),
    HIGHNOTE_PING("highnotePing");

    private String actionName;

    ActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

}
