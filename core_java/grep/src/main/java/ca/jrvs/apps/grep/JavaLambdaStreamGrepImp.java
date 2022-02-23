package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaLambdaStreamGrepImp extends JavaGrepImp {
    public static void main(String[] args) {
        if(args.length <= 3){
            JavaLambdaStreamGrepImp jLSGI = new JavaLambdaStreamGrepImp();
            jLSGI.setRegEx(args[0]);
            jLSGI.setOutFile(args[2]);
            jLSGI.setRootPath(args[1]);

            try{
                jLSGI.process();
            } catch (Exception ex){
                jLSGI.lggr.error("Error: Unable to process",ex);
            }
        }
    }//end main

    @Override
    public List<String> readLines(File inputFile){
        List<String> lineRead = new ArrayList<String>();
        try {
            Stream<String> fileStream = Files.lines(inputFile.toPath());
            fileStream.forEach(line -> lineRead.add(line));
        } catch(IOException ex) {
            lggr.error("Error file reading",ex);
        }
        return lineRead;
    }

    @Override
    public List<File> listFiles(String rootDir){
        File root = new File(rootDir);
        List<File> fileList = new ArrayList<File>();
        root.listFiles(f -> fileList.add(f));
        return fileList;
    }
}
