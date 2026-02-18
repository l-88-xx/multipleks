package pl.edu.agh.mwo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.mwo.Screening.FORMATTER;


/**
 * Klasa reprezentuje pojedyncze kino w sieci multipleksów.
 * Przechowuje informacje o nazwie, adresie, salach oraz zaplanowanych seansach.
 */

public class Cinema {

    private String name;
    private String address;
    private List<Hall> halls = new ArrayList<>();
    private List<Screening> screenings = new ArrayList<>();

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public void addScreening(Screening screening) {
        screenings.add(screening);
    }

    public void printProgramme() {
        System.out.println(" * Repertuar kina " + name);
        for (Screening s : screenings) {
            System.out.println(
                    s.getMovie().getTitle() +
                            " | " + s.getDateTime().format(FORMATTER) +
                            " | Sala " + s.getHall().getNumber()
            );
        }
    }

    public List<Screening> getWeeklyProgramme() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekLater = now.plusDays(7);

        return screenings.stream()
                .filter(s -> s.getDateTime().isAfter(now) &&
                        s.getDateTime().isBefore(weekLater))
                .toList();
    }

    public List<Screening> findMovie(String title) {

        return screenings.stream()
                .filter(s -> s.getMovie().getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public List<Screening> getScreeningList() {
        return screenings;
    }

    public void setScreeningList(List<Screening> screeningList) {
        this.screenings = screeningList;
    }
}
