package es.sm2.rookieproyect.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FRUITS")
public class Fruit {

    @Id
    @Column (name = "id_fruit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFruit;

    @Column(name = "name", columnDefinition = "varchar2(50)", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "varchar2(256)")
    private String description;

    @Column(name = "price_per_kg", columnDefinition = "number 4.2", nullable = false)
    private Integer pricePerKg;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public Long getIdFruit() {
        return idFruit;
    }

    public void setIdFruit(Long idFruit) {
        this.idFruit = idFruit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Integer pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
