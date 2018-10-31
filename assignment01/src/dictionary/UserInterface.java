package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class UserInterface {
	
	Dictionary<String, String> dict;
	
	public void readCommand() {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String str = scanner.nextLine();
			String[] splitStr = str.split("\\s+");
			String command = splitStr[0];
			
			if (command.equals("create")) {
				if (splitStr.length == 1) {
					createDictionary("");
				} else {
					createDictionary(splitStr[1]);
				}
			} else if (command.equals("test")) {
				testPerformance();
			} else if (dict == null) {
				System.out.println("Create a dictionary first!");
			} else if (command.equals("read")) {
				if (splitStr.length == 2) {
					read(splitStr[1]);
				} else if (splitStr.length == 3) {
					read(splitStr[1], splitStr[2]);
				}
			} else if (command.equals("p")) {
				print();
			} else if (command.equals("s")) {
				if (splitStr.length == 2) {
					search(splitStr[1]);
				}
				
			} else if (command.equals("i")) {
				if (splitStr.length == 3) {
					insert(splitStr[1], splitStr[2]);
				}
			} else if (command.equals("r")) {
				if (splitStr.length == 2) {
					remove(splitStr[1]);
				}
			} else if (command.equals("exit")) {
				scanner.close();
				exit();
			}
		} 
	}
	
	public void read (String name) {
		try {
			Scanner input = new Scanner(new File(name));
			
			while (input.hasNextLine())
			{
				String[] newEntry = (input.nextLine()).split("\\s+");
				dict.insert(newEntry[0], newEntry[1]);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void createDictionary(String dictType) {
		if (dictType.equals("") || dictType.equals("SortedArrayDictionary")) {
			dict = new SortedArrayDictionary<>();
		} else if (dictType.equals("HashDictionary")) {
			dict = new HashDictionary<>();
		} else if (dictType.equals("BinaryTreeDictionary")) {
			System.out.println("test");
			//dict = new BinaryTreeDictionary<>();
		}
	}
	
	public void read (String n, String fileName) {
		try {
			Scanner input = new Scanner(new File(fileName));
			int numberOfLines = Integer.valueOf(n);
			int counter = 0;
			
			while (input.hasNextLine() && counter < numberOfLines) {
				String[] newEntry = (input.nextLine()).split("\\s+");
				dict.insert(newEntry[0], newEntry[1]);
				counter++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void print() {
		for (Dictionary.Entry<String, String> entry : dict) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	public void search(String key) {
		System.out.println(dict.search(key));
	}
	
	public void insert(String key, String value) {
		dict.insert(key, value);
	}
	
	public void remove(String key) {
		dict.remove(key);
	}
	
	public void exit() {
		System.out.println("Programm wurde beendet");
		System.exit(0);
	}
	
	public void testPerformance() {
		PerformanceTester test = new PerformanceTester();
		test.testPerformance();
	}
}