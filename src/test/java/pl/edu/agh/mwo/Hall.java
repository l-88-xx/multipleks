package pl.edu.agh.mwo;

import java.util.List;

/**
 * Klasa reprezentuje salę kinową,
 * zawierającą miejsca oraz informację, czy obsługuje filmy 3D.
 */

public class Hall {

    private int number;
    private List<Seat> seats;
    private boolean supports3D;

    public Hall(int number, List<Seat> seats, boolean supports3D) {
        this.number = number;
        this.seats = seats;
        this.supports3D = supports3D;
    }

    public Hall(int number, List<Seat> seats) {
        this(number, seats, false);
    }

    public Seat findSeat(String code) {
        for (Seat seat : seats) {
            if ((seat.getRow() + seat.getNumber()).equals(code)) {
                return seat;
            }
        }
        return null;
    }

    public boolean supports3D() {
        return supports3D;
    }

    public void setSupports3D(boolean supports3D) {
        this.supports3D = supports3D;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
