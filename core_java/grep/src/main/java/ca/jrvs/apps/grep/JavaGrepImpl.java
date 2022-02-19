package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaGrepImpl implements JavaGrep {

    final Logger lggr = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;   //target pattern to find
    private String rootPath;//parent file directory
    private String outFile; //target output file

    public static void main(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFIle");
        }

        //use default logger config
        //BasicConfigurator.configure();
        JavaGrepImpl jGrepImpl = new JavaGrepImpl();
        //get user variables
        jGrepImpl.setRegEx(args[0]);
        jGrepImpl.setRootPath(args[1]);
        jGrepImpl.setOutFile(args[2]);

        try{    //do the actual work process
            jGrepImpl.process();
        }catch(Exception ex){
            jGrepImpl.lggr.error("Error: Unable to process",ex);
        }
    }

    @Override
    public void process() throws IOException {
        List<String> greppedLines = new ArrayList<String>();
        for ( File aFile : listFiles(rootPath) ){
            for (String line : readLines(aFile) ){
                if(containsPattern(line)){
                    greppedLines.add(line);
                }//end match if
            } //end read file lines
        }//end loop over files
        writeToFile(greppedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        File root = new File(rootDir);
        List<File> fileList = new ArrayList<File>();
        for (File f : root.listFiles()) {
            fileList.add(f);
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lineRead = new ArrayList<String>();
        try {
            FileReader fReader = new FileReader(inputFile);
            BufferedReader bReader = new BufferedReader(fReader);
            String readLine;
            //read from file
            while((readLine = bReader.readLine()) != null){
                lineRead.add(readLine);
            }
            bReader.close();
            fReader.close();
        } catch(IOException ex) {
            lggr.error("Error file reading",ex);
        }
        return lineRead;
    }

    @Override
    public boolean containsPattern(String line) {
        if(line.matches(getRegEx()))
            return true;
        else
            return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        FileWriter fWriter = new FileWriter(outFile);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        //write to file
        for(String lineToWrite : lines){
            bWriter.write(lineToWrite);
            bWriter.newLine();
        }
        bWriter.close();
        fWriter.close();
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegEx() {
        return regex;
    }

    @Override
    public void setRegEx(String regEx) {
        this.regex = regEx;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
