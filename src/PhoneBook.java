import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class PhoneBook {

	private TreeMap<String, Contact> phones;
	private TreeMap<Integer, ArrayList<Contact>> calls;

	public PhoneBook(File file) {
		phones = new TreeMap<String, Contact>();
		calls = new TreeMap<Integer, ArrayList<Contact>>(new CallsComparator());
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] result = line.split(",");
				if (result.length == 2) {
					String name = result[0];
					String number = result[1];
					add(name, number);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(String name, String number) {
		if (!(phones.containsKey(name))) {
			try {
				Contact contact = new Contact(name, number);
				phones.put(name, contact);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(String name) {
		if (phones.containsKey(name)) {
			Contact contact = phones.get(name);
			Integer currentCalls = contact.getCalls();
			if (calls.containsKey(currentCalls)) {
				if (calls.get(currentCalls).contains(contact)) {
					calls.get(currentCalls).remove(contact);
				}
			}
			phones.remove(name);
		}
	}

	public void printPhoneBook() {
		Iterator iterator = phones.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Contact value = phones.get(key);
			System.out.println("Name : " + key + " Number : " + value.getNumber());
		}
	}

	public void call(String name) {
		if (phones.containsKey(name)) {
			Contact contact = phones.get(name);
			Integer currentCalls = contact.getCalls();
			if (calls.containsKey(currentCalls)) {
				if (calls.get(currentCalls).contains(contact)) {
					calls.get(currentCalls).remove(contact);
				}
			}

			contact.addCall();
			Integer newCalls = contact.getCalls();
			if (!(calls.containsKey(newCalls))) {
				
				ArrayList<Contact> contactCalls = new ArrayList<Contact>();
				calls.put(newCalls, contactCalls);
			}
			calls.get(newCalls).add(contact);

		} else {
			System.out.println("Contact does not exist!");
		}
	}

	public void PrintFiveMostCalled() {
		int count = 0;
		Iterator iterator = calls.keySet().iterator();

		while (iterator.hasNext() && count <= 5) {
			Integer key = (Integer) iterator.next();
			System.out.println(key);
			ArrayList<Contact> contacts = calls.get(key);

			for (Contact contact : contacts) {
				if (count <= 5) {
					System.out.println(contact.getName());
					count++;
				} else {
					break;
				}
			}
		}

	}

}
