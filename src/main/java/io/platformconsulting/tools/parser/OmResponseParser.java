package io.platformconsulting.tools.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class OmResponseParser {

    private static final String BAR = "-----";

    @Autowired
    private Yaml yaml;

    public OmResponseParser() { }

    public OmResponseParser(Yaml yaml) {
        this.yaml = yaml;
    }

    public void parse(String source,String target) throws IOException {
        //open up the source
        FileInputStream fis = new FileInputStream(source);
        //read and parse
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        StringBuilder certificate = new StringBuilder();
        boolean readCertificate = false;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("+-")) {
                //ignore
                continue;
            }//end if

            if (line.contains("BEGIN CERTIFICATE")) {
                //clean the line
                certificate.append(cleanLine(line));
                readCertificate = true;
                continue;
            }//end if

            if (line.contains("END CERTIFICATE")) {
                certificate.append(cleanLine(line));
                readCertificate = false;
                continue;
            }//end if

            if (readCertificate) {
                certificate.append(cleanLine(line));
                certificate.append("\n");
            }//end if
        }//end while
        reader.close();
        fis.close();
        //add the certificate to a yaml structure
        Map<String,Object> configYaml = yamlStructurce(certificate.toString());
        //now we have the certificate --> write it out
        yaml.dump(configYaml,new FileWriter(target));
    }

    private Map<String,Object> yamlStructurce(String certificate) {
        Map<String,Object> parent = new HashMap<>();
        //construct the attributes
        Map<String,Object> server = new HashMap<>();
        server.put("url","127.0.0.1");
        server.put("ca_cert",certificate);
        server.put("alias","alias");
        //add
        parent.put("environments", Arrays.asList(server));
        //return
        return parent;
    }

    private String cleanLine(String raw) {
        String line;
        //check
        if ((raw.contains("BEGIN CERTIFICATE") || (raw.contains("END CERTIFICATE")))) {
            line = raw.substring(raw.indexOf(BAR),raw.lastIndexOf(BAR)+BAR.length()) + "\n";
        } else {
            line = raw.replace("|","").replace(" ","");
        }//end if
        return line;
    }

}
