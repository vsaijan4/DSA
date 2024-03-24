import java.io.*;
import java.util.*;
import java.math.*;

class Main {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());
        while(d-->0) {
            String[] first_line = br.readLine().split(" ");
            int n = Integer.parseInt(first_line[0]);
            int k = Integer.parseInt(first_line[1]);
            String[] second_line = br.readLine().split(" ");
            int[] s = new int[k];
            for(int i=0;i<k;i++) {
                s[i]=Integer.parseInt(second_line[i]);
            }
            System.out.println(solve(n, k, s));
        }
    }
    public static int solve(int n, int k, int[] s) {
        int ans = 0;
        long max = (long)Math.pow(2, (2*n));
        for(long i=0;i<max;i++) {
            String str = Long.toBinaryString(i);
            int diff = 2*n - str.length();
            String prefix = "";
            for(int j=0; j<diff; j++) {
                prefix += "0";
            }
            str = prefix + str;
            
            if(validExpression(str) && checkBits(str, k, s)) {
                ans++;
            }
        }
        return ans;
    }

    public static boolean checkBits(String input, int k, int[] s) {
        for(int i=0;i<k;i++) {
            if(input.charAt(s[i]-1) != '0') {
                return false;
            }
        }
        return true;
    }

    public static boolean validExpression(String s) {
        Stack<Character> st = new Stack<>();
        int l = s.length();
        for(int i=0;i<l;i++) {
            if(s.charAt(i) == '0') {
                st.push('0');
            } else {
                if(st.isEmpty()) return false;
                st.pop();
            }
        }
        return st.isEmpty();
    }
}