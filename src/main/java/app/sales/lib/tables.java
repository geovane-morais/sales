package app.sales.lib;

import app.sales.domain.entity.product;
import app.sales.domain.entity.project;
import app.sales.domain.entity.user;
import app.sales.domain.repository.repositoryProducts;
import app.sales.domain.repository.repositoryProjects;
import app.sales.domain.repository.repositoryUsers;
import app.sales.model.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class tables {
    private repositoryProjects repProjects;
    private repositoryProducts repProducts;
    private repositoryUsers repUsers;

    public String insert(dtoInsertData dto){
        switch (dto.getTable()) {
            case "projets":
                repProjects.save(new project(null, "", "", "", ""));
                break;
            case "utilisateur":
                repUsers.save(new user(null, 0,"" ,"", "", "", "", "", "", ""));
                break;
            case "produits":
                repProducts.save(new product(null,"","",0, "0", 0));
                break;
            default:
                return "{\"response\":\"Not found\"}";
        }
        return "{\"response\":\"" + dto.getTable() + "add new row!\"}";
    }

    @Modifying
    public String update(dtoUpdateData dto){
        System.out.println(dto.toString());
        switch (dto.getTable_database()) {
            case "projets":
                project proj = repProjects.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Codigo invalido" + dto.getId()));
                proj.setData(dto.getColuna(),dto.getValor());
                repProjects.save(proj);
                break;
            case "utilisateur":
                user us = repUsers.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Codigo invalido" + dto.getId()));
                us.setData(dto.getColuna(),dto.getValor());
                repUsers.save(us);
                break;
            case "produits":
                product prod = repProducts.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Codigo invalido" + dto.getId()));
                prod.setData(dto.getColuna(),dto.getValor());
                repProducts.save(prod);
                break;
            default:
                return "{\"response\":\"Not found\"}";
        }
        return "{\"response\":\"" + dto.getTable_database() + "update row (" + dto.getId() + ")!\"}";
    }
}
