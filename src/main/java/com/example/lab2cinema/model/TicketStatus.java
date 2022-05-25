package com.example.lab2cinema.model;

public enum TicketStatus {
    NotBought,Bought;
    public static TicketStatus getFromNumber(Integer number) {
        switch (number) {
            case 1:
                return NotBought;
            case 2:
                return Bought;
        }
        throw new IllegalArgumentException();
    }

    public int toInt() {
        switch (this) {
            case NotBought: return 1;
            case Bought: return 2;
            default: throw new IllegalArgumentException();
        }
    }
}
