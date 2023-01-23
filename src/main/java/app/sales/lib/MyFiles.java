package app.sales.lib;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class MyFiles {
    @Autowired
    private Environment env;

    public String getFile(String fileName){
        String UPLOAD_DIR = env.getProperty("file.location.share");
        String data = "";
        String st;
        BufferedReader[] bf = {new BufferedReader(new InputStreamReader(System.in)),
                               new BufferedReader(new InputStreamReader(System.in))};
        try {
            if (fileName.contains("gerenciamento")) {
                bf[0] = new BufferedReader(new FileReader(ResourceUtils.getFile(UPLOAD_DIR +"templates/templateGerenciamentoIndex.html")));
            } else {
                bf[0] = new BufferedReader(new FileReader(ResourceUtils.getFile(UPLOAD_DIR +"templates/templateIndex.html")));
                bf[1] = new BufferedReader(new FileReader(ResourceUtils.getFile(UPLOAD_DIR +"templates/" + fileName)));
            }
            while ((st = bf[0].readLine()) != null) {
                data = data.concat(st);
                if (st.contains("<!-- INSERT CONTENT OF PAGE -->")){
                    while ((st = bf[1].readLine()) != null){
                       data = data.concat(st);
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public String saveFile(MultipartFile[] multipartFile) {
        for (int x=0; x < multipartFile.length; x++) {
            try {
                String UPLOAD_DIR = env.getProperty("file.location.share") + "static/images/cliente/";
                List<Object> files = Files.list(
                        Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/cliente/")
                ).map(name -> name.toFile().getName()).collect(Collectors.toList());

                if (files.isEmpty()) {
                    multipartFile[x].transferTo(new File(System.getProperty("user.dir") + UPLOAD_DIR + "1.png"));
                } else {
                    multipartFile[x].transferTo(new File(
                            System.getProperty("user.dir") + UPLOAD_DIR + (files.toArray().length + 1) + ".png"
                    ));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return "Save image!";
    };

    public Stream<Object> listedImages(){
        String UPLOAD_DIR = env.getProperty("file.location.share") + "static/images/cliente/";
        Stream<Object> result;
        try {
            result = Files.list(Paths.get(System.getProperty("user.dir") + UPLOAD_DIR )).map(name -> name.toFile().getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

