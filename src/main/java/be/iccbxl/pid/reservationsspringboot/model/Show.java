package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.slugify.Slugify;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private boolean bookable;

    private double price;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Show(String title, String description, String posterUrl, Location location, boolean bookable, double price) {
        this.slug = new Slugify().slugify(title);
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.location = location;
        this.bookable = bookable;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
        this.slug = new Slugify().slugify(title);
    }

    public void setLocation(Location location) {
        if (this.location != null) {
            this.location.removeShow(this);
        }
        this.location = location;
        if (location != null) {
            location.addShow(this);
        }
    }
}
