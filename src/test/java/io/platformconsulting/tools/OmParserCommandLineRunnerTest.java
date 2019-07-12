package io.platformconsulting.tools;

import io.platformconsulting.tools.parser.OmResponseParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.support.membermodification.MemberModifier;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

import static org.junit.Assert.*;

public class OmParserCommandLineRunnerTest {

    @Test
    public void run() throws Exception {
        //setup the supporting classes
        OmResponseParser omResponseParser = new OmResponseParser(new Yaml());
        OmParserCommandLineRunner omParserCommandLineRunner = new OmParserCommandLineRunner();
        //inject the dependency
        MemberModifier.field(OmParserCommandLineRunner.class,"omResponseParser").set(omParserCommandLineRunner,omResponseParser);
        //setup the file
        String source = new ClassPathResource("source.cert").getFile().getAbsolutePath();
        //get the target
        String target = "./output.yaml";
        //execute
        omParserCommandLineRunner.run(source,target);
        //test
        assertTrue(new File(target).length() > 0);
    }
}