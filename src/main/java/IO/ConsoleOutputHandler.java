package IO;

import java.util.List;

/**
 * Not even worth commenting on, use should be obvious
 */
public class ConsoleOutputHandler implements IOutputHandler {
    private final boolean FORMAT_WITH_NEWLINES = true;  //Controls whether the output should be separated to multiple lines
    private final int MAX_PAYLOAD_LENGTH = 1750;        //The maximum length of each payload

    @Override
    public void outputString(String s) {
        System.out.println(s);
    }

    @Override
    public void outputStrings(List<String> s) {
        for(String o : s)
            System.out.println(o);
    }

    /**
     * The length check is 1900 rather than 2000 because Java and the system seem to count string length differently
     * @param s Strings to output
     */
    @Override
    public void outputWithSMFormatting(List<String> s) {
        String tmp = s.get(0);
        StringBuilder payload = new StringBuilder("$sm " + tmp);

        int t = 1; //Counter variable
        for(int i = 1; i < s.size(); i++) {
            tmp = s.get(i);
            if(payload.length() + tmp.length() > t * MAX_PAYLOAD_LENGTH) {
                payload.append("\n\n$sm ").append(tmp);
                t++;
            } else {
                if(FORMAT_WITH_NEWLINES)
                    payload.append("\n");
                payload.append("$").append(tmp);
            }
        }
        System.out.println(payload);
    }
}
