package com.example.lab2cinema.model;

public enum TicketStatus {
    NotBought,Bought;

    public int ToInt() {
        switch (this) {
            case NotBought: return 0;
            case Bought: return 1;
            default: throw new IllegalArgumentException();
        }
    }
}
