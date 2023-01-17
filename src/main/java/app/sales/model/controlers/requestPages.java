package app.sales.model.controlers;

import app.sales.domain.entity.product;
import app.sales.domain.entity.project;
import app.sales.domain.entity.user;
import app.sales.domain.repository.repositoryProducts;
import app.sales.domain.repository.repositoryProjects;
import app.sales.domain.repository.repositoryUsers;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.server.PathContainer;
import org.springframework.web.bind.annotation.*;
import app.sales.lib.MyFiles;
import app.sales.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class requestPages {

    private String[] validDatabases = {"utilisateur","projets","produits"};

    private final MyFiles myFiles;
    private repositoryProjects repProjects;
    private repositoryProducts repProducts;
    private repositoryUsers repUsers;

    @PostMapping(value = "projects/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<project> requestForProjects() {
        return repProjects.findAll();
    }

    @PostMapping(value = "products/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<product> requestForProducts() {
        return repProducts.findAll();
    }

    @PostMapping(value = "users/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<user> requestForUsers() {
        return repUsers.findAll();
    }

    @PostMapping(value = "project", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<project> requestForSingleProject() {
        Integer id = 1;
        project proj = repProjects.findById(id)
                                  .orElseThrow(()-> new RuntimeException("Codigo invalido"+id));
        //fazer request das fotos de apenas um projeto com URL project?id=1
        return null;
    }

    @PostMapping(value = "gerenciamento/imagens/load_data")
    public void teste(){}
    @PostMapping(value = "gerenciamento/imagens/upload")
    public String uploadImages(/*@RequestParam() MultipartFile multipartFile*/) {
        String curDir = System.getProperty("user.dir");
        System.out.println("DIRETORIO: "+curDir);

        /*try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }*/
        return "ok";
    }

    @PostMapping(value = "gerenciamento/insert",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                 produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String requestForGenInsert(dtoInsertData dto) {
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
                return "Not found";
        }
        return dto.getTable() + "add new row!";
    }

    @PostMapping(value = "gerenciamento/update",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String requestForGenUpdate(dtoUpdateData dto) {
        switch (dto.getTable_database()) {
            case "projets":
                project proj = repProjects.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Codigo invalido" + dto.getId()));
                System.out.println(dto);
                break;
            case "utilisateur":
                System.out.println(dto);
                break;
            case "produits":
                System.out.println(dto);
                break;
            default:
                return "Not found";
        }
        return dto.getTable_database() + "update row (" + dto.getId() + ")!";
    }
}

