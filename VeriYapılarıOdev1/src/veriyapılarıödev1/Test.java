/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapılarıödev1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author X550V
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\X550V\\Desktop\\metin.txt");
        LinkedList list = new LinkedList(file);
        list.print();
        list.printMainNodes();
        list.printListNodes();
        char s = 115;
        char v = 118;
        list.ardisikKarakterler(v);
        list.enAzArdisik();
        list.enAzArdisik(v);
        list.enCokArdisik();
        list.enCokArdisik(v);
        list.ardisikKarakterler(s);
        list.enCokArdisik(s);
     


    }

}
