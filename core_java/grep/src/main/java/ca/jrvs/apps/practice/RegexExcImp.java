package ca.jrvs.apps.practice;

import java.util.Locale;

public class RegexExcImp implements RegexExc{
    @Override
    public boolean matchJPEG(String filename){
        if ( filename.toLowerCase(Locale.ROOT).matches(".jpg$") || filename.toLowerCase(Locale.ROOT).matches(".jpeg$") )
            return true;
        return false;
    }

    @Override
    public boolean matchIP(String ip) {
        if( ip.matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}$") )
            return true;
        return false;
    }

    @Override
    public boolean isEmptyLine(String line) {
        if( line.matches("\\s*") )
            return true;
        return false;
    }
}
