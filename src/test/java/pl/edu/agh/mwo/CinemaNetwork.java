package pl.edu.agh.mwo;

import java.util.List;

/**
 * Klasa reprezentuje sieć kin (multipleksów), w której może znajdować się wiele lokalizacji.
 */

public class CinemaNetwork {

    private String name;
    private List<Cinema> cinemas;

    public CinemaNetwork(String name, List<Cinema> cinemaList) {
        this.name = name;
        this.cinemas = cinemaList;
    }

    public List<Screening> getProgramme() {

        return cinemas.stream()
                .flatMap(c -> c.getScreeningList().stream())
                .toList();
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }
}
