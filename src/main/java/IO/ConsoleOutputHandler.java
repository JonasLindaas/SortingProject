package IO;

import java.util.List;

/**
 * Not even worth commenting on, use should be obvious
 */
public class ConsoleOutputHandler implements IOutputHandler {
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
        String payload      = s.get(0);
        StringBuilder tmp   = new StringBuilder("$sm " + payload);

        int t = 1; //Counter var
        for(int i = 1; i < s.size(); i++) {
            payload = s.get(i);
            if(tmp.length() + payload.length() > t * 1900) {
                tmp.append("\n\n$sm ").append(payload);
                t++;
            } else
                tmp.append("\n$").append(payload);
        }
        System.out.println(tmp);
    }
}
