package dictionary;

import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.sun.istack.internal.logging.Logger;

import java.io.*;

public class WoerterbuchAnwendung {
	
	public void read(File f) {
		LineNumberReader in = null;
		try {
			in = new LineNumberReader(new FileReader(f));
			String line;
			while ((line = in.readLine()) != null) {
				String[] sf = line.split(" ");
				if (sf.length == 2) {
					insert(sf[0], sf[1]); //leerer Zusatz
				} else if (sf.length == 3) {
					insert(sf[0], sf[2]);
				}
			}
			in.close();
		} catch (IOException ex) {
			Logger.getLogger(getClass());
		}
	}

	

	public static void main(String[] args) 
		throws FileNotFoundException, IOException {
		Dictionary<String, String> wb;
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		
		
		Scanner in = new Scanner(System.in);
		int i;
		i = in.nextInt();
		
		if(i == 1)
			wb = new SortedArrayDictionary<String, String>();
		else
			wb = new HashDictionary<String, String>();
		
		wb.read(new File("C:\\Users\\Björnus\\workspace\\algo\\assignment01\\src\\dictionary"));
		wb.insert("lesen", "read");
		wb.insert("schreiben", "write");
		wb.remove("lesen");
		
		
		System.out.println(wb.search("lesen"));

	}

}
