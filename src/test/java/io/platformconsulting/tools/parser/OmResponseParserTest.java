package io.platformconsulting.tools.parser;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

import static org.junit.Assert.*;

public class OmResponseParserTest {

    @Test
    public void parse() throws Exception {
        //instantiate
        OmResponseParser omResponseParser = new OmResponseParser(new Yaml());
        //get the location
        assertTrue(new ClassPathResource("source.cert").exists());
        String source = new ClassPathResource("source.cert").getFile().getAbsolutePath();
        //get the target
        String target = "./output.yaml";
        //execute
        omResponseParser.parse(source,target);
        //now check
        assertTrue(new File(target).length() > 0);
    }
}