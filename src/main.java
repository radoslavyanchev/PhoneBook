import java.io.File;


public class main {

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		String filesPath = projectPath + "\\src\\Files\\";
		String fileName = "PhoneBook.txt";
		PhoneBook phoneBook = new PhoneBook(new File(filesPath + fileName));
		phoneBook.printPhoneBook();
		phoneBook.call("Pesho Peshev");
		phoneBook.call("Pesho Peshev");
		phoneBook.call("Pesho Peshev");
		phoneBook.call("Miro");
		phoneBook.call("Mimi");
		phoneBook.call("Joro");
		phoneBook.call("Joro");
		
		phoneBook.PrintFiveMostCalled();
		
	}
	
}
