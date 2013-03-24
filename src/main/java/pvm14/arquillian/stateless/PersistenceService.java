package pvm14.arquillian.stateless;

import java.util.List;

import pvm14.arquillian.persistence.Record;

public interface PersistenceService {
	void insert(String string);

	List<Record> selectAll();
}
