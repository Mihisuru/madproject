package com.example.etrain;
public class Ticket {
    String ticketCode;
    String getIn;
    String getOff;
    String clas;
    String date;
    String NOP;

    public Ticket(String ticketCode, String getIn, String getOff, String clas, String date, String NOP) {
        this.ticketCode = ticketCode;
        this.getIn = getIn;
        this.getOff = getOff;
        this.clas = clas;
        this.date = date;
        this.NOP = NOP;
    }

    public Ticket() {
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getGetIn() {
        return getIn;
    }

    public void setGetIn(String getIn) {
        this.getIn = getIn;
    }

    public String getGetOff() {
        return getOff;
    }

    public void setGetOff(String getOff) {
        this.getOff = getOff;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNOP() {
        return NOP;
    }

    public void setNOP(String NOP) {
        this.NOP = NOP;
    }
}
