package pvm14.arquillian.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Record {

	@Id
	private String string;

	public Record() {
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
