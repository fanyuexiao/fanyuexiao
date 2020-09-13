package aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class Fyx {
    public void say(){
        System.out.println("i am fyx");
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String classFilePath = "com/fasterxml/jackson/annotation/JacksonAnnotation.class";
        URL systemResource = ClassLoader.getSystemResource(classFilePath);
        URL systemResource1 = ClassLoader.getSystemResource("");
        System.out.println(systemResource1.getPath());
        System.out.println(systemResource.getPath());
        if (systemResource == null) {
            System.out.println("不存在该目录: " + classFilePath);
            return;
        }
        File file = new File(systemResource.toURI());
        System.out.println(file.toPath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getAbsolutePath());
    }
}
