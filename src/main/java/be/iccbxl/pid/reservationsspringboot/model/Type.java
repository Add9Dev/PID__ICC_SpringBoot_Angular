package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "The type name must not be empty.")
    @Column(nullable = false, unique = true) // Ajout de contraintes pour la base de données
    private String type;

    @ManyToMany
    @JoinTable(
            name = "artist_type",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists = new ArrayList<>();

    public Type(String type) {
        this.type = type;
    }

    public void addArtist(Artist artist) {
        if (!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.addType(this); // Mise à jour bidirectionnelle
        }
    }

    public void removeArtist(Artist artist) {
        if (this.artists.remove(artist)) {
            artist.getTypes().remove(this); // Mise à jour bidirectionnelle
        }
    }

    @Override
    public String toString() {
        return "Type [id=" + id + ", type=" + type + ", artists=" + artists.size() + "]";
    }
}
