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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class user{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "function admin cannot be null")
    @Column(name = "is_admin")
    private Integer isAdmin;

    @NotNull(message = "name Id cannot be null")
    @Column(name = "name")
    private String name;
    @NotNull(message = "email cannot be null")
    @Column(name = "email")
    private String email;
    @NotNull(message = "phone cannot be null")
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @NotNull(message = "password cannot be null")
    @Column(name = "password")
    private String password;

    @Column(name = "temp_password")
    private String tempPassword;

    public void setData(String coluna, String data) {
        switch (coluna){
            case "name":
                this.name = data;
                break;
            case "email":
                this.email = data;
                break;
            case "phone":
                this.phone = data;
                break;
            case "password":
                this.password = data;
                break;
            case "isAdmin":
                this.isAdmin =  Integer.parseInt(data);
                break;
            default:
        }
    }
}