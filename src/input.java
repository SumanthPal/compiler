import java.util.HashMap;
import java.util.Scanner;

public class input {
    String st;

    public input(String st) {
        this.st = st;
    }

    public void inputting(Scanner s, HashMap<String, Boolean> booleans,
                          HashMap<String, Integer> integers,
                          HashMap<String, Double> floats,
                          HashMap<String, String> Strings) {
        int startType = st.indexOf(" ", 1)+1;
        int endType = st.indexOf(" ", startType +1);

        String type = st.substring(startType, endType);

        if (type.equals("str")) {
            //gets user input
            String scan = s.nextLine();

            //gets variable name
            int endName = st.indexOf(";");
            String name = st.substring(endType+1, endName);

            Strings.put(name, scan);
        } else if (type.equals("boo")) {
            boolean scan = s.nextBoolean();

            //gets variable name
            int endName = st.indexOf(";");
            String name = st.substring(endType+1, endName);

            booleans.put(name, scan);
        } else if (type.equals("doub")) {
            double scan = s.nextDouble();

            int endName = st.indexOf(";");
            String name = st.substring(endType+1, endName);

            floats.put(name, scan);
        } else if (type.equals("num")) {
            int scan = s.nextInt();

            int endName = st.indexOf(";");
            String name = st.substring(endType+1, endName);

            integers.put(name,scan);
        }
    }



}
