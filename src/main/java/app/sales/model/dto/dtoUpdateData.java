package app.sales.model.dto;

import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class dtoUpdateData {

    private String[] validColumns = {
            "nome", "descricao", "thumbnail", "galeria", "preco_x_metro", "disponibilidade",
            "email", "passe", "telefone", "endereco", "estado_sigla", "cidade"
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
}
