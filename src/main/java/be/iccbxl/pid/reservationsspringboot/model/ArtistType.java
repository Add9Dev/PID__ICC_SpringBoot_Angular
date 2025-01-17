package be.iccbxl.pid.reservationsspringboot.model;


import com.github.slugify.Slugify;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "artist_type")
public class ArtistType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToMany
    @JoinTable(
            name = "artist_type_show",
            joinColumns = @JoinColumn(name = "artist_type_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id"))
    private List<Show> shows = new ArrayList<>();

    public ArtistType(Artist artist, Type type, List<Show> shows) {
        this.artist = artist;
        this.type = type;
        this.shows = shows;
    }

    public ArtistType addShow(Show show) {
        if (!this.shows.contains(show)) {
            this.shows.add(show);
            show.addArtistType(this);
        }
        return this;
    }

    public ArtistType removeShow(Show show) {
        if (this.shows.contains(show)) {
            this.shows.remove(show);
            show.getArtistTypes().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "ArtistType [id=" + id + ", artist=" + artist + ", type=" + type
                + ", shows=" + shows.size() + "]";
    }
}