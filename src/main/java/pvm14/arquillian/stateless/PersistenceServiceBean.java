package pvm14.arquillian.stateless;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pvm14.arquillian.persistence.Record;

@Stateless
@Local(PersistenceService.class)
@Remote(PersistenceService.class)
public class PersistenceServiceBean implements PersistenceService {

	@PersistenceContext(name = "test")
	private EntityManager em;

	public void insert(String string) {
		Record record = new Record();
		record.setString(string);
		em.persist(record);
	}

	@SuppressWarnings("unchecked")
	public List<Record> selectAll() {
		return em.createQuery("select r from Record r").getResultList();
	}
}