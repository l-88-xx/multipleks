package pl.edu.agh.mwo;

/**
 * Klasa reprezentuje film wyświetlany w kinie.
 * Zawiera podstawowe informacje: tytuł, czas trwania oraz
 * informację, czy film jest przeznaczony do projekcji w technologii 3D.
 */

public class Movie {

    private String title;
    private int duration;
    private boolean is3D;

    public Movie(String title, int duration, boolean is3D) {
        this.title = title;
        this.duration = duration;
        this.is3D = is3D;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean is3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }
}
