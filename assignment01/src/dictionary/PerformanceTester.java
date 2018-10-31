package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import dictionary.Dictionary.Entry;

public class PerformanceTester {
	
	Dictionary<String, String> dict;
	ArrayList<Entry<String, String>> entryList;
	LinkedList<String> germanWords8k = new LinkedList<>();
	LinkedList<String> germanWords16k = new LinkedList<>();
	LinkedList<String> englishWords8k = new LinkedList<>();
	LinkedList<String> englishWords16k = new LinkedList<>();
	
	public PerformanceTester() {
		entryList = getEntryList();
		
		for (int i = 0; i < 8000; i++) {
			germanWords8k.add(entryList.get(i).getKey());
			englishWords8k.add(entryList.get(i).getValue());
		}
		
		for (Entry<String, String> entry : entryList) {
			germanWords16k.add(entry.getKey());
			englishWords16k.add(entry.getValue());
		}
	}
	
	public void testPerformance() {
		dict = new SortedArrayDictionary<>();
		testDictionary();
		
		dict = new HashDictionary<>();
		testDictionary();
		
		//dict = new BinaryTreeDictionary<>();
		testDictionary();
	}
	
	public void testDictionary() {
		System.out.println("-------Performance Test-------");
		System.out.println("Dictionary type:  " + dict.getClass());
		testCreationTime();
		testSuccessfulSearchTime();
		testUnsuccessfulSearchTime();
	}
	
	public void testCreationTime() {
		long before = System.currentTimeMillis();
		read("8000");
		long after = System.currentTimeMillis();
		long difference = after - before;
		System.out.println("Aufbau mit ca. 8000 Einträgen: " + difference + "ms");
		
		before = System.currentTimeMillis();
		read("20000");
		after = System.currentTimeMillis();
		difference = after - before;
		System.out.println("Aufbau mit ca. 16000 Einträgen: " + difference + "ms");
	}
	
	public void testSuccessfulSearchTime() {
		long before = System.currentTimeMillis();
		for (String word : germanWords8k) {
			dict.search(word);
		}
		long after = System.currentTimeMillis();
		long difference = after - before;
		System.out.println("Erfolgreiche Suche mit ca. 8000 Einträgen: " + difference + "ms");
		
		before = System.currentTimeMillis();
		for (String word : germanWords16k) {
			dict.search(word);
		}
		after = System.currentTimeMillis();
		difference = after - before;
		System.out.println("Erfolgreiche Suche mit ca. 16000 Einträgen: " + difference + "ms");
	}
	
	public void testUnsuccessfulSearchTime() {
		long before = System.currentTimeMillis();
		for (String word : englishWords8k) {
			dict.search(word);
		}
		long after = System.currentTimeMillis();
		long difference = after - before;
		System.out.println("Nicht erfolgreiche Suche mit ca. 8000 Einträgen: " + difference + "ms");
		
		before = System.currentTimeMillis();
		for (String word : englishWords16k) {
			dict.search(word);
		}
		after = System.currentTimeMillis();
		difference = after - before;
		System.out.println("Nicht erfolgreiche Suche mit ca. 16000 Einträgen: " + difference + "ms");
	}
	
	public ArrayList<Entry<String,String>> getEntryList() {
		ArrayList<Entry<String,String>> list = new ArrayList<>();
		
		try {
			Scanner input = new Scanner (new File("dtengl.txt"));
			
			while(input.hasNextLine())
			{
				String[] newEntry = (input.nextLine()).split("\\s+");
				list.add(new Entry<>(newEntry[0], newEntry[1]));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void read (String n) {
		try {
			Scanner input = new Scanner(new File("dtengl.txt"));
			int numberOfLines = Integer.valueOf(n);
			int counter = 0;
			
			while(input.hasNextLine() && counter < numberOfLines)
			{
				String[] newEntry = (input.nextLine()).split("\\s+");
				dict.insert(newEntry[0], newEntry[1]);
				counter++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}