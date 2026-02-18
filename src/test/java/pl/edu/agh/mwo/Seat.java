package pl.edu.agh.mwo;

import java.util.Objects;

/**
 * Klasa reprezentuje pojedyncze miejsce w sali kinowej.
 */

public class Seat {

    private String row;
    private int number;
    private SeatType type;

    public Seat(String row, int number, SeatType type) {
        this.row = row;
        this.number = number;
        this.type = type;
    }

    public enum SeatType {
        STANDARD,
        VIP
    }

    public String getCode() {
        return row + number;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seat seat)) return false;
        return number == seat.number &&
                Objects.equals(row, seat.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number);
    }
}
