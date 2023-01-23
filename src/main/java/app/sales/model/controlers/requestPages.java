package app.sales.model.controlers;

import app.sales.domain.entity.product;
import app.sales.domain.entity.project;
import app.sales.domain.entity.user;
import app.sales.domain.repository.repositoryProducts;
import app.sales.domain.repository.repositoryProjects;
import app.sales.domain.repository.repositoryUsers;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.sales.lib.*;
import app.sales.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class requestPages {
    private final MyFiles myFiles;
    private tables table;
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
    public project requestForSingleProject(@RequestParam(required = false) Integer id) {
        project proj = repProjects.findById(id)
                                  .orElseThrow(()-> new RuntimeException("Codigo invalido"+id));
        return proj;
    }

    @GetMapping(value = "gerenciamento/imagens/load_data")
    public Stream<Object> loadImages(){
        return myFiles.listedImages();
    }
    @PostMapping(value = "gerenciamento/imagens/upload")
    public String uploadImages(@RequestParam("file") MultipartFile[] multipartFile) {
        return myFiles.saveFile(multipartFile);
    }

    @PostMapping(value = "gerenciamento/insert",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                 produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String requestForGenInsert(dtoInsertData dto) {
        return table.insert(dto);
    }

    @PostMapping(value = "gerenciamento/update",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String requestForGenUpdate(dtoUpdateData dto) {
        return table.update(dto);
    }
}

