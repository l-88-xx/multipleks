package pl.edu.agh.mwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuje klienta zarejestrowanego w systemie.
 * Klient może kupować bilety oraz przechowywać historię zakupów.
 */

public class Customer {

    private String name;
    private String email;
    final private List<Ticket> tickets = new ArrayList<>();

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public List<Ticket> buy(Screening screening, String... seatCodes) {
        Reservation reservation = screening.reservePlaces(this, seatCodes);
        return reservation.pay();
    }

    public void addTickets(List<Ticket> newTickets) {
        tickets.addAll(newTickets);
    }

    public void removeTicket(Ticket ticket) { tickets.remove(ticket); }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
