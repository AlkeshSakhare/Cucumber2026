package test;


import java.util.Arrays;

public class Interview2 {
    public static void main(String[] args) {

        //shift zero's at start of list of integer values;
        int a[] = {89, 0, 0, 34, 0, 23, 360, 0, 200};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[i] != 0) {
                    int b1 = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = b1;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
