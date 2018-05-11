package es.sm2.rookieproyect.model;

import java.time.LocalDateTime;

public class FruitDto {

    private Long idFruit;
    private String name;
    private String description;
    private Integer pricePerKg;
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
