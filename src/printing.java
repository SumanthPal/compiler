import java.util.HashMap;

public class printing {
    String st;

    public printing(String st) {
        this.st = st;
    }
    public void print(HashMap<String, Integer> integers,
                      HashMap<String, Boolean> booleans,
                      HashMap<String, Double> floats,
                      HashMap<String, String> Strings) {
        int start = st.indexOf("(")+1;
        char arr[] = st.toCharArray();
        try {
            for (int m = start; m < st.indexOf(")"); m++) {

                //prints if val is string
                if (arr[m] == '"') {
                    System.out.print(st.substring(m + 1, st.indexOf('"', m + 1)));
                    m
                            = st.indexOf('"', m + 1) + 1;
                    //prints if its a var

                } else if (arr[m] == '_') {
                    int secondoccurence = st.indexOf("_", m + 1);
                    if (integers.containsKey(st.substring(m + 1, secondoccurence))) {
                        System.out.print(integers.get(st.substring(m + 1, secondoccurence)));
                    } else if (floats.containsKey(st.substring(m + 1, secondoccurence))) {
                        System.out.print(floats.get(st.substring(m + 1, secondoccurence)));
                    } else if (Strings.containsKey(st.substring(m + 1, secondoccurence))) {
                        System.out.print(Strings.get(st.substring(m + 1, secondoccurence)));
                    } else if (booleans.containsKey(st.substring(m+1, secondoccurence))) {
                        System.out.print(booleans.get(st.substring(m + 1, secondoccurence)));
                    } else {
                        System.out.println("error! Val does not exist!");
                    }
                    m = secondoccurence + 1;
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Invalid Syntax. Please check for parenthesis or commas");
        }
    }
}
