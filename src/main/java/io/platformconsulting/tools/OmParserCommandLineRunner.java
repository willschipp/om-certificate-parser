package io.platformconsulting.tools;

import io.platformconsulting.tools.parser.OmResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Profile("!test")
public class OmParserCommandLineRunner implements CommandLineRunner {

    @Autowired
    OmResponseParser omResponseParser;

    @Override
    public void run(String... args) throws Exception {
        Assert.isTrue(args.length == 2,"Please provide source and target file locations");
        omResponseParser.parse(args[0],args[1]);
        System.out.println("Done!");
    }
}
