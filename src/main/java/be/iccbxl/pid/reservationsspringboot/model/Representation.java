package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="representations")
public class Representation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    /**
     * Date de création de la représentation
     */
    private LocalDateTime when;

    /**
     * Lieu de prestation de la représentation
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "reservations",
            joinColumns = @JoinColumn(name = "representation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public Representation() {
    }

    //…

    public List<User> getUsers() {
        return users;
    }

    public Representation addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.addRepresentation(this);
        }

        return this;
    }

    public Representation removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            user.getRepresentations().remove(this);
        }

        return this;
    }

    @Override
    public String toString() {
        return "Representation [id=" + id + ", show=" + show + ", when=" + when
                + ", location=" + location + "]";
    }

    public void setShow(Object o) {
    }

    public void setLocation(Location location) {
    }

    public OffsetDateTime getWhen() {
        return null;
    }
}