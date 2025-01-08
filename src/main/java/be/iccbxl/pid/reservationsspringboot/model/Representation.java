package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "representations")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"show", "location"}) // Évite les boucles infinies lors de l'affichage
public class Representation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    /**
     * Date et heure de la représentation.
     */
    @Column(nullable = false)
    private LocalDateTime when;

    /**
     * Lieu de la prestation de la représentation.
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    // Constructeur personnalisé
    public Representation(Show show, LocalDateTime when, Location location) {
        this.show = show;
        this.when = when;
        this.location = location;
    }
}
