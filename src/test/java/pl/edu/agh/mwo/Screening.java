package pl.edu.agh.mwo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuje pojedynczy seans filmowy w wybranej sali i o konkretnej godzinie.
 * Umożliwia rezerwację miejsc, zakup biletów oraz zwroty.
 */

public class Screening {

    final private Movie movie;
    final private Hall hall;
    final private LocalDateTime dateTime;
    final private List<Reservation> reservations = new ArrayList<>();

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Screening(Movie movie, Hall hall, LocalDateTime dateTime) {
        this.movie = movie;
        this.hall = hall;
        this.dateTime = dateTime;

        validate3D();
    }

    private void validate3D() {
        if (movie.is3D() && !hall.supports3D()) {
            throw new IllegalStateException("Sala nie obsługuje seansów 3D");
        }
    }

    public boolean isSeatReserved(Seat seat) {
        for (Reservation r : reservations) {
            if (r.getSeats().contains(seat)) {
                return true;
            }
        }
        return false;
    }

    public Reservation reservePlaces(Customer customer, String... seatCodes) {

        List<Seat> selectedSeats = new ArrayList<>();

        for (String code : seatCodes) {
            Seat seat = hall.findSeat(code);
            if (seat == null) {
                throw new IllegalArgumentException("Miejsce " + code + " nie istnieje");
            }
            if (isSeatReserved(seat)) {
                throw new IllegalStateException("Miejsce " + code + " już zarezerwowane");
            }
            selectedSeats.add(seat);
        }

        Reservation reservation = new Reservation(this, customer, selectedSeats);
        reservations.add(reservation);

        return reservation;
    }

    public Reservation reservePlaces(String... seatCodes) {
        return reservePlaces(null, seatCodes);
    }

    public Reservation reservePlaces(Seat... seats) {
        List<Seat> selectedSeats = new ArrayList<>();

        for (Seat seat : seats) {

            if (!hall.getSeats().contains(seat)) {
                throw new IllegalArgumentException("Miejsce nie należy do tej sali.");
            }
            if (seat == null) {
                throw new IllegalArgumentException("Nie ma takiego miejsca.");
            }
            if (isSeatReserved(seat)) {
                throw new IllegalStateException("Miejsce " + seat.getCode() + " już zarezerwowane");
            }
            selectedSeats.add(seat);
        }

        Reservation reservation = new Reservation(this, null, selectedSeats);
        reservations.add(reservation);
        return reservation;
    }

    // zakup bez rezerwacji
    public List<Ticket> buyTickets(String... seatCodes) {
        Reservation reservation = reservePlaces(null, seatCodes);
        return reservation.pay();
    }

    public void refund(Ticket ticket) {
        for (Reservation r : reservations) {
            if (r.getCustomer() != null) {
                r.getCustomer().removeTicket(ticket);
            }
            if (r.getSeats().contains(ticket.getSeat())) {
                r.cancel();
                reservations.remove(r);
                break;
            }
        }
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
    }


    public List<Seat> getAvailableSeats() {
        List<Seat> free = new ArrayList<>();

        for (Seat seat : hall.getSeats()) {
            if (!isSeatReserved(seat)) {
                free.add(seat);
            }
        }
        return free;
    }

    public double calculatePrice(Seat seat) {

        double basePrice = 20;

        if (movie.is3D()) {
            basePrice += 5;
        }

        if (seat.getType() == Seat.SeatType.VIP) {
            basePrice += 10;
        }
        return basePrice;
    }

    public Movie getMovie() {
        return movie;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
