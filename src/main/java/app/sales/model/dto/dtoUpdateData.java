package app.sales.model.dto;

import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class dtoUpdateData {

    private String[] validColumns = {
            "name", "description", "thumbnail", "gallery", "pricePerMeter", "isAvailable",
            "email", "password", "phone", "addrres", "state", "city","photo"
    };

    private int id;
    private String coluna;

    private String table_database;
    private String valor;

    public void setColuna(String column){
        boolean columnIsInvalid = Arrays.stream(validColumns).filter(v -> v.equals(column)).collect(Collectors.toList()).isEmpty();
        if(columnIsInvalid) {
            throw new RuntimeException("[[[ Invalid column("+column+") in \"dtoUpdateData\" ]]]");
        }
        this.coluna = column;
    }

    @Override
    public String toString() {
        return "dtoUpdateData{" +
                "id=" + id +
                ", coluna='" + coluna + '\'' +
                ", table_database='" + table_database + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
