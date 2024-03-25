import java.util.ArrayList;
import java.util.HashMap;

public class arithmeticoperations {
    String type;
    String st;

    public arithmeticoperations(String type, String st) {
        this.type = type;
        this.st = st;
    }

    public void arithmetics(HashMap<String, Integer> integers, HashMap<String, Double> floats) {
        if (type.equals("add")) {
            String name = st.substring(1, st.indexOf("=") - 1);
            int startPos = st.indexOf("(") + 1;
            int endPos = st.indexOf(")");
            char arr[] = st.toCharArray();

            for (int m = startPos; m < endPos; m++) {
                if (arr[m] == '_') {
                    int secondoccurence = st.indexOf("_", m + 1);
                    if (integers.containsKey(st.substring(m + 1, secondoccurence))) {
                        int adder = integers.get(st.substring(m + 1, secondoccurence));
                        integers.put(name, integers.getOrDefault(name, 0) + adder);

                        m = secondoccurence + 1;
                    } else if (floats.containsKey(st.substring(m + 1, secondoccurence))) {
                        double adder = floats.get(st.substring(m + 1, secondoccurence));
                        floats.put(name, floats.getOrDefault(name, 0.0) + adder);
                    }
                } else if ((arr[m] != ',')) {
                    String secVar;

                    if (st.indexOf(',', m + 1) > 0) {
                        secVar = st.substring(m, st.indexOf(',', m + 1));
                    } else {
                        secVar = st.substring(m, st.indexOf(')'));

                    }
                    if (floats.containsKey(name)) {
                        double adder = Double.parseDouble(secVar);
                        floats.put(name, floats.getOrDefault(name, 0.0) + adder);

                    } else if (integers.containsKey(name)) {
                        int adder = Integer.parseInt(secVar);
                        integers.put(name, (integers.getOrDefault(name, 0) + adder));

                    }
                }
            }

        } else if (type.equals("subtract")) {
            String name = st.substring(1, st.indexOf("=") - 1);
            int startPos = st.indexOf("(") + 1;
            int endPos = st.indexOf(")");
            char arr[] = st.toCharArray();

            for (int m = startPos; m < endPos; m++) {
                if (arr[m] == '_') {
                    int secondoccurence = st.indexOf("_", m + 1);
                    if (integers.containsKey(st.substring(m + 1, secondoccurence))) {
                        int adder = integers.get(st.substring(m + 1, secondoccurence));
                        integers.put(name, integers.getOrDefault(name, 0) - adder);

                        m = secondoccurence + 1;
                    } else if (floats.containsKey(st.substring(m + 1, secondoccurence))) {
                        double adder = floats.get(st.substring(m + 1, secondoccurence));
                        floats.put(name, floats.getOrDefault(name, 0.0) - adder);
                    }
                } else if ((arr[m] != ',')) {
                    String secVar;

                    if (st.indexOf(',', m + 1) > 0) {
                        secVar = st.substring(m, st.indexOf(',', m + 1));
                    } else {
                        secVar = st.substring(m, st.indexOf(')'));

                    }
                    if (floats.containsKey(name)) {
                        double adder = Double.parseDouble(secVar);
                        floats.put(name, floats.getOrDefault(name, 0.0) - adder);

                    } else if (integers.containsKey(name)) {
                        int adder = Integer.parseInt(secVar);
                        integers.put(name, integers.getOrDefault(name, 0) - adder);

                    }
                }
            }

        } else if (type.equals("division")) {
            String name = st.substring(1, st.indexOf("=") - 1);
            int startPos = st.indexOf("(") + 1;
            int endPos = st.indexOf(")");
            char arr[] = st.toCharArray();

            for (int m = startPos; m < endPos; m++) {
                if (arr[m] == '_') {
                    int secondoccurence = st.indexOf("_", m + 1);
                    if (integers.containsKey(st.substring(m + 1, secondoccurence))) {
                        int adder = integers.get(st.substring(m + 1, secondoccurence));
                        integers.put(name, integers.getOrDefault(name, 0) / adder);

                        m = secondoccurence + 1;
                    } else if (floats.containsKey(st.substring(m + 1, secondoccurence))) {
                        double adder = floats.get(st.substring(m + 1, secondoccurence));
                        floats.put(name, floats.getOrDefault(name, 0.0) / adder);
                    }
                } else if ((arr[m] != ',')) {
                    String secVar;

                    if (st.indexOf(',', m + 1) > 0) {
                        secVar = st.substring(m, st.indexOf(',', m + 1));
                    } else {
                        secVar = st.substring(m, st.indexOf(')'));

                    }
                    if (floats.containsKey(name)) {
                        double adder = Double.parseDouble(secVar);
                        floats.put(name, floats.getOrDefault(name, 0.0) / adder);

                    } else if (integers.containsKey(name)) {
                        int adder = Integer.parseInt(secVar);
                        integers.put(name, integers.getOrDefault(name, 0) / adder);

                    }
                }
            }
        } else if (type.equals("multiplication")) {
            String name = st.substring(1, st.indexOf("=") - 1);
            int startPos = st.indexOf("(") + 1;
            int endPos = st.indexOf(")");
            char arr[] = st.toCharArray();

            for (int m = startPos; m < endPos; m++) {
                if (arr[m] == '_') {
                    int secondoccurence = st.indexOf("_", m + 1);
                    if (integers.containsKey(st.substring(m + 1, secondoccurence))) {
                        int adder = integers.get(st.substring(m + 1, secondoccurence));
                        integers.put(name, integers.getOrDefault(name, 0) * adder);

                        m = secondoccurence + 1;
                    } else if (floats.containsKey(st.substring(m + 1, secondoccurence))) {
                        double adder = floats.get(st.substring(m + 1, secondoccurence));
                        floats.put(name, floats.getOrDefault(name, 0.0) * adder);
                    }
                } else if ((arr[m] != ',')) {
                    String secVar;

                    if (st.indexOf(',', m + 1) > 0) {
                        secVar = st.substring(m, st.indexOf(',', m + 1));
                    } else {
                        secVar = st.substring(m, st.indexOf(')'));

                    }
                    if (floats.containsKey(name)) {
                        double adder = Double.parseDouble(secVar);
                        floats.put(name, floats.getOrDefault(name, 0.0) * adder);

                    } else if (integers.containsKey(name)) {
                        int adder = Integer.parseInt(secVar);
                        integers.put(name, integers.getOrDefault(name, 0) * adder);

                    }
                }
            }
        } else if (type.equals("modulus")) {
            String name = st.substring(1, st.indexOf("=") - 1);
            int startPos = st.indexOf("(") + 1;
            int endPos = st.indexOf(")");
            char arr[] = st.toCharArray();

            for (int m = startPos; m < endPos; m++) {
                if (arr[m] == '_') {
                    int secondoccurence = st.indexOf("_", m + 1);
                    if (integers.containsKey(st.substring(m + 1, secondoccurence))) {
                        int adder = integers.get(st.substring(m + 1, secondoccurence));
                        integers.put(name, integers.getOrDefault(name, 0) % adder);

                        m = secondoccurence + 1;
                    } else if (floats.containsKey(st.substring(m + 1, secondoccurence))) {
                        double adder = floats.get(st.substring(m + 1, secondoccurence));
                        floats.put(name, floats.getOrDefault(name, 0.0) % adder);
                    }
                } else if ((arr[m] != ',')) {
                    String secVar;

                    if (st.indexOf(',', m + 1) > 0) {
                        secVar = st.substring(m, st.indexOf(',', m + 1));
                    } else {
                        secVar = st.substring(m, st.indexOf(')'));

                    }
                    if (floats.containsKey(name)) {
                        double adder = Double.parseDouble(secVar);
                        floats.put(name, floats.getOrDefault(name, 0.0) % adder);

                    } else if (integers.containsKey(name)) {
                        int adder = Integer.parseInt(secVar);
                        integers.put(name, integers.getOrDefault(name, 0) % adder);

                    }
                }
            }


        }
    }
}
