package pl.edu.agh.mwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuje rezerwację miejsc na konkretny seans.
 * Może być powiązana z klientem.
 */

public class Reservation {

    private Customer customer;
    private List<Seat> seats;
    private ReservationStatus status;
    final private Screening screening;

    public Reservation(Screening screening, Customer customer, List<Seat> seats) {
        this.screening = screening;
        this.customer = customer;
        this.seats = seats;
        this.status = ReservationStatus.RESERVED;
    }

    public List<Ticket> pay() {
        this.status = ReservationStatus.PAID;

        List<Ticket> tickets = new ArrayList<>();

        for (Seat seat : seats) {
            Ticket ticket = new Ticket(seat,screening, screening.calculatePrice(seat));
            tickets.add(ticket);
        }

        if (customer != null) {
            customer.addTickets(tickets);
        }
        return tickets;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}