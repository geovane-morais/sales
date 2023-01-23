package app.sales.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class project{
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

    @NotNull(message = "photo cannot be null")
    @Column(name = "photo")
    private String photo;

    @NotNull(message = "gallerry cannot be null")
    @Column(name = "gallery")
    private String gallery;

    public void setData(String coluna, String data) {
        switch (coluna){
            case "name":
                this.name = data;
                break;
            case "description":
                this.description = data;
                break;
            case "photo":
                this.photo = data;
                break;
            case "gallery":
                this.gallery = data;
                break;
            default:
        }
    }
}