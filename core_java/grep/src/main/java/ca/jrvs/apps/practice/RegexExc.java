package ca.jrvs.apps.practice;

public interface RegexExc {

    /**
     * return true if filename extension is jpeg or jpg (case insensitive)
     * @param filename
     * @return
     */
    public boolean matchJPEG(String filename);

    /**
     * return true if IP is valid
     * ip is valid from 0.0.0.0 to 999.999.999.999
     * @param ip
     * @return
     */
    public boolean matchIP(String ip);

    /**
     * return true if line is empty
     * @param line
     * @return
     */
    public boolean isEmptyLine(String line);
}
