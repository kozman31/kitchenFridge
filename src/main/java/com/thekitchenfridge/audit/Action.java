package com.thekitchenfridge.audit;

public enum Action {
    INSERT("INSERT"), UPDATE("UPDATE"), DELETE("DELETE");

    private String value;
    private Action(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
