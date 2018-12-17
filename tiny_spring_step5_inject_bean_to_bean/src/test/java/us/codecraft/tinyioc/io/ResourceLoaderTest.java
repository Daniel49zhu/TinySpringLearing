package us.codecraft.tinyioc.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoaderTest {
    @Test
    public void test() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("tinyioc.xml");
        InputStream inputStream = resource.getInputStream();

        byte[] buffer = new byte[1<<10];
        while(inputStream.read(buffer)!=-1) {
            System.out.println(new String(buffer));
        }
        inputStream.close();
    }
}
