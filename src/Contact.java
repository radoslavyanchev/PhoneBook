import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
	final String NAME_REGEX = "^([A-Za-z ]+)$";
	final String PHONE_REGEX = "^((\\+|00)359|0)8[7-9][2-9](\\d){6}$";
	private String name;
	private String number;
	private int calls;

	public Contact(String name, String number) throws Exception {
		this.setName(name);
		this.setNumber(number);
		this.calls = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		String trimmedName = name.trim();
		if (trimmedName.matches(NAME_REGEX)) {
			this.name = trimmedName;
		} else {
			throw new Exception("Invalid name: " + trimmedName);
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) throws Exception {
		String trimmedNumber = number.trim();
		Pattern pattern = Pattern.compile(PHONE_REGEX);
		Matcher matcher = pattern.matcher(trimmedNumber);
		if (matcher.matches()) {
			trimmedNumber = trimmedNumber.replaceAll("(00359|0)", "+359");
			this.number = trimmedNumber;
		} else {
			throw new Exception("Invalid number: " + trimmedNumber);
		}
	}

	public int getCalls() {
		return calls;
	}

	public void addCall() {
		this.calls++;
	}
}
