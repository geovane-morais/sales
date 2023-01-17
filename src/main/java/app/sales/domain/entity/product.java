package app.sales.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Integer id;

    @NotNull(message = "name cannot be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "description cannot be null")
    @Column(name = "description")
    private String description;

    @NotNull(message = "price cannot be null")
    @Column(name = "price_per_meter")
    private double pricePerMeter;

    @NotNull(message = "path_image cannot be null")
    @Column(name = "path_image")
    private String pathImage;


    @NotNull(message = "avaliable cannot be null")
    @Column(name = "is_available")
    private int isAvailable;
}