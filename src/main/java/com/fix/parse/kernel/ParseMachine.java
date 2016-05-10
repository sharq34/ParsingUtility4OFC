package com.fix.parse.kernel;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by yangy1 on 5/9/2016.
 */
public class ParseMachine {

    private static String sourceFolder= null;
    private static String outputFolder="d:\\output\\";


    public ParseMachine(){
        ResourceBundle bundle = ResourceBundle.getBundle("cfg");
        sourceFolder = bundle.getString("dir");

//        BasicConfigurator.configure();
        PropertyConfigurator.configure("log4j.properties");

    }

    public List<File> fetchFolder() {
        File f = new File(outputFolder);
        return Arrays.asList(f.listFiles());
    }

    public static void main(String[] args) throws IOException, ParseException {
        ParseMachine machine = new ParseMachine();

        ParseKernel kernel = new ParseKernel();

        //kernel.convertDOCXToTXT(sourceFolder,outputFolder);

        kernel.parse(machine.fetchFolder());

        kernel.vocabularyList.print();
    }
}
