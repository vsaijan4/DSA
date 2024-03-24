package usaco;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class shuffle {
    static BufferedReader br;
    static PrintWriter out;
    final static String COVISHIELD = ". Vaccine: COVISHIELD.";
    final static String COVAXIN = ". Vaccine: COVAXIN.";

    static void solve() throws IOException {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> a;
        String s, time = "", hosp = "", date;
        s = br.readLine();
        while (!s.equals("END")) {
            if (s.length() == 5) {
                time = s;
            } else if (s.contains("Pin")) {
                hosp = s;
                hosp = hosp.replaceFirst(COVAXIN, "");
                hosp = hosp.replaceFirst(COVISHIELD, "");
                hosp = hosp.replaceFirst("^([0-9]+\\.)", "");
                hosp = hosp.strip();
                hosp = hosp.replaceFirst("\\.$","");
            } else if (s.contains("slots")) {
                date = s.replace("Total ", "");
                date = date.replace(" slots are available on ", " ");
                date = date.strip();
                if (map.containsKey(hosp)) {
                    map.get(hosp).add(String.format("%10s", date) + " " + time);
                } else {
                    a = new ArrayList<>();
                    a.add(String.format("%10s", date) + " " + time);
                    map.put(hosp, a);
                }
            }
            s = br.readLine();
        }
        map.forEach((key, value) -> {
            if(key.contains("Yashoda")) {
                out.println(key);
                for (String item : value) {
                    out.println(item);
                }
                out.println();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("in.txt"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
        solve();
        out.close();
    }
}