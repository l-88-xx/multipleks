package pl.edu.agh.mwo;

public class Ticket {

    private Seat seat;
    private Screening screening;
    private double price;

    public Ticket(Seat seat, Screening screening, double price) {
        this.seat = seat;
        this.screening = screening;
        this.price = price;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
