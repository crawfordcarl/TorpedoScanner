/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pol {

    public static void main(String[] args) {
        try {

//            char[][] data = readFile("C:\\pol\\TestData.blf");
//            char[][] torpedo = readFile("C:\\pol\\SlimeTorpedo.blf");
//            char[][] starship = readFile("C:\\pol\\Starship.blf");

            char[][] data1 = readFile("C:\\pol\\SlimeTorpedo.blf");
            char[][] data3 = readFile("C:\\pol\\TestData.blf");
            
            int a = data3.length - (data1.length-1);
            int b = data3[0].length - (data1[0].length-1);
            System.out.println("should be " + (a * b));
            PrintWriter out = new PrintWriter(new File("C:\\pol\\log.txt"));
            
            bigCompare(data3, data1, out);
            
            out.close();
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static char[][] readFile(String file) {
        char[][] dat = null;
        String txt = "";
        int count = 0, i = 0;
        try {
            BufferedReader r = new BufferedReader(new FileReader(new File(file)));
            while (r.readLine() != null) {
                count++;
            }

            r = new BufferedReader(new FileReader(new File(file)));
            txt = r.readLine();
            dat = new char[count][txt.length()];
            dat[i] = txt.toCharArray();
            while ((txt = r.readLine()) != null) {
                i++;
                dat[i] = txt.toCharArray();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dat;
    }

    public static void printData(char[][] dat) {
        System.out.println("BEGIN");
        for (char[] cc : dat) {
            for (char c : cc) {
                System.out.print(c);
            }
            System.out.println("");
        }
        System.out.println("END");
    }

    public static void polMethod(char[][] data, char[][] torpedo, PrintWriter out) {
        int i, j;
        int k, l;
        for (k = 0; k < data.length - torpedo.length; k++) {

            for (i = 0; i < torpedo.length; i++) {
                for (l = 0; l < data[k].length - torpedo[i].length; l++) {
                    for (j = 0; j < torpedo[i].length; j++) {
                        out.println(torpedo[i][j]);
                        out.println(data[i + k][j + l]);
                        out.println("The value of i is " + i + " the value of j is " + j + " the value of k is " + k + " and l is " + l);
                    }
                    out.println("next line");
                }
            }
        }
    }

    public static double smallCompare(char[][] a, char[][] b) {
        /* ensure both are equal dimensions*/
        assert a.length == b.length : "a and b are not equal height";
        assert a[0].length == b[0].length : "a and b are not equal width";

        double pixels = a.length * a[0].length;
        double countSimilar = 0;
//        System.out.println("pixels " + pixels);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == b[i][j]) {
                    countSimilar++;
                }
            }
        }

        return ((countSimilar / pixels) * 100.0);
    }
    
    public static void bigCompare(char[][] a, char[][] b, PrintWriter out) {
        
        
        /* padding around the data as the torpedo cannot be outside, i.e. half a torpedo */
        for (int i = 0; i < a.length - (b.length-1); i++) {
                for (int j = 0; j < a[0].length - (b[0].length-1); j++) {
                    
                    /* get a section of the data equal to torpedo */
                    char[][] c = new char[b.length][b[0].length];
                    for (int k = 0; k < c.length; k++) {
                        for (int l = 0; l < c[0].length; l++) {
                            c[k][l] = a[i+k][j+l]; 
                        }
                    }
                    
                    out.print(smallCompare(c, b) + " ");
//                    System.out.println("here?");
                }
                out.println();
            }
    }
    
   
    
}
