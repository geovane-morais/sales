package app.sales.model.controlers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.sales.lib.MyFiles;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class sendPagesHtml {
    private final MyFiles myFiles;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getIndex() {
        return myFiles.getFile("home.html");
    }

    @GetMapping(value = "/{page}", produces = MediaType.TEXT_HTML_VALUE)
    public String getPages(@PathVariable(value = "page") String page) {
        return myFiles.getFile(page+".html");
    }

    @GetMapping(value = "/gerenciamento",produces = MediaType.TEXT_HTML_VALUE)
    public String getManager() {
        return myFiles.getFile("gerenciamento_index.html");
    }

    @GetMapping(value = "/gerenciamento/{page}", produces = MediaType.TEXT_HTML_VALUE)
    public String getPagesManager(@PathVariable(value = "page") String page) {
        return myFiles.getFile("gerenciamento_"+page+".html");
    }
}
