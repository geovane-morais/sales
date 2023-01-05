package app.sales.lib;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@AllArgsConstructor
@Service
public class MyFiles {
    public String getFile(String fileName){
        String data = "";
        try{
            File file = ResourceUtils.getFile("classpath:templates/" + fileName);
            InputStream in = new FileInputStream(file);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {data = data.concat(myReader.nextLine());}}
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
