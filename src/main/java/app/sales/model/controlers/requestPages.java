package app.sales.model.controlers;

import app.sales.domain.entity.product;
import app.sales.domain.entity.project;
import app.sales.domain.entity.user;
import app.sales.domain.repository.repositoryProducts;
import app.sales.domain.repository.repositoryProjects;
import app.sales.domain.repository.repositoryUsers;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.sales.lib.MyFiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class requestPages {
    private final MyFiles myFiles;
    private repositoryProjects repProjects;
    private repositoryProducts repProducts;
    private repositoryUsers repUsers;

    @PostMapping(value = "projects/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<project> requestForProjects() {
        List<project> projects = repProjects.findAll();
        return projects;
    }

    @PostMapping(value = "products/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<product> requestForProducts() {
        List<product> products = repProducts.findAll();
        return products;
    }

    @PostMapping(value = "users/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<user> requestForUsers() {
        List<user> users = repUsers.findAll();
        return users;
    }

    @PostMapping(value = "project", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<project> requestForSingleProject() {
        Integer id = 1;
        project proj = repProjects.findById(id)
                                  .orElseThrow(()-> new RuntimeException("Codigo invalido"+id));
        //fazer request das fotos de apenas um projeto com URL project?id=1
        return null;
    }
}

