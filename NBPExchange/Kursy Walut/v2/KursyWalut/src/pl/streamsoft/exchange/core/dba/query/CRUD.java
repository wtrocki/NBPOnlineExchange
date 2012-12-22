package pl.streamsoft.exchange.core.dba.query;

import pl.streamsoft.exchange.core.util.UtilLogger;

import com.avaje.ebean.Ebean;

public class CRUD {
	private static CRUD INSTANCE;

	public static CRUD inst() {
		return INSTANCE == null ? INSTANCE = new CRUD() : INSTANCE;
	}

	public void store(Object o) {
		try {
			Ebean.save(o);
		} catch (Exception e) {
			UtilLogger.logError(e.getMessage());
		}
	}

	public void update(Object o) {
		try {
			Ebean.update(o);
		} catch (Exception e) {
			UtilLogger.logError(e.getMessage());
		}
	}

	public void delete(Object o) {
		try {
			Ebean.delete(o);
		} catch (Exception e) {
			UtilLogger.logError(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteAll(Class clazz, String tableName) {
		Ebean.beginTransaction();
		try {
			Ebean.createUpdate(clazz, "delete from " + tableName).execute();
		} catch (Exception e) {
			UtilLogger.logError(e.getMessage());
		}
		Ebean.commitTransaction();
	}
}
