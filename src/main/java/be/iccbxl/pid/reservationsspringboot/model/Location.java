package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String slug;

    @NotEmpty(message = "La désignation ne doit pas être vide.")
    @Size(min = 3, max = 100, message = "La désignation doit comporter entre 3 et 100 caractères.")
    private String designation;

    @NotEmpty(message = "L'adresse ne doit pas être vide.")
    @Size(min = 5, max = 200, message = "L'adresse doit comporter entre 5 et 200 caractères.")
    private String address;

    @ManyToOne
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;

    @Pattern(regexp = "^(https?://)?[a-zA-Z0-9.-]+(\\.[a-zA-Z]{2,6})([/\\w.-]*)*/?$",
            message = "L'URL du site web est invalide.")
    private String website;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$",
            message = "Le numéro de téléphone est invalide.")
    private String phone;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows = new ArrayList<>();

    // Constructeur personnalisé avec slugification
    public Location(String designation, String address, Locality locality, String website, String phone) {
        Slugify slg = new Slugify();
        this.slug = slg.slugify(designation);
        this.designation = designation;
        this.address = address;
        this.locality = locality;
        this.website = website;
        this.phone = phone;
    }

    // Setter personnalisé pour la désignation
    public void setDesignation(String designation) {
        this.designation = designation;

        // Générer le slug automatiquement
        Slugify slg = new Slugify();
        this.slug = slg.slugify(designation);
    }

    // Setter personnalisé pour la localité
    public void setLocality(Locality locality) {
        if (this.locality != null) {
            this.locality.removeLocation(this); // Supprimer l'ancienne association
        }
        this.locality = locality;
        if (locality != null) {
            this.locality.addLocation(this); // Ajouter la nouvelle association
        }
    }

    // Méthodes pour gérer les relations avec Show
    public void addShow(Show show) {
        if (!this.shows.contains(show)) {
            this.shows.add(show);
            show.setLocation(this);
        }
    }

    public void removeShow(Show show) {
        if (this.shows.remove(show)) {
            show.setLocation(null);
        }
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", slug=" + slug + ", designation=" + designation
                + ", address=" + address + ", locality=" + locality + ", website="
                + website + ", phone=" + phone + ", shows=" + shows.size() + "]";
    }
}
