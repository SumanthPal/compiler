import java.util.ArrayList;
import java.util.HashMap;

public class forloop {
    String st;

    public forloop(String st) {
        this.st = st;
    }
    public void looping(int i, boolean forloopactive,ArrayList<String> forloopskeys, ArrayList forloops, HashMap<String, Integer> integers) {
        String name = varname(st, "for");


        //default case; if only range is being put
        try {
            if (!st.contains(",")) {
                //puts the counter variable in the integers
                integers.put(name, 0);
                //adds a key
                forloopskeys.add(name);
                //end value
                int loop = Integer.parseInt(st.substring(st.indexOf("(") + 1, st.indexOf(")")));
                forloopactive = true;
                //1st adds end value, then line#, then increment
                forloops.add(loop);
                forloops.add(i);
                forloops.add(1);
            } else {
                //case if increment, start, and range have been changed
                if (st.indexOf(",", st.indexOf(",") + 1) > 0) {
                    //find first val
                    int start = st.indexOf("(") + 1;
                    int end = st.indexOf(",");
                    int startingval = Integer.parseInt(st.substring(start, end));
                    //finds the end val
                    int start1 = st.indexOf(",") + 2;
                    int end1 = st.indexOf(",", st.indexOf(",") + 1);
                    int endval = Integer.parseInt(st.substring(start1, end1));
                    //finds the increment
                    int start2 = end1 + 2;
                    int end2 = st.indexOf(")");
                    int increment = Integer.parseInt(st.substring(start2, end2));
                    forloops.add(endval);
                    forloops.add(i);
                    forloops.add(increment);
                    forloopactive = true;


                    integers.put(name, startingval);
                    //adds a key
                    forloopskeys.add(name);

                } else {
                    int start = st.indexOf("(") + 1;
                    int end = st.indexOf(",");
                    int startingval = Integer.parseInt(st.substring(start, end));
                    //finds the end val
                    int start1 = st.indexOf(",") + 2;
                    int end1 = st.indexOf(")");
                    int endval = Integer.parseInt(st.substring(start1, end1));

                    forloops.add(endval);
                    forloops.add(i);
                    forloops.add(1);

                    forloopactive = true;

                    integers.put(name, startingval);
                    //adds a key
                    forloopskeys.add(name);

                }
            }
        } catch(Exception e) {
            throw new RuntimeException("ur stupid. make sure to check for syntax");
        }
    }
    public String varname(String st, String type) {
        int start = st.indexOf(type) +type.length()+1;
        int end = st.indexOf(" ", start) ;
        return st.substring(start,end);
    }
}
