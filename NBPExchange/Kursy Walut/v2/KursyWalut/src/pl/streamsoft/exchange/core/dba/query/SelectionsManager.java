package pl.streamsoft.exchange.core.dba.query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pl.streamsoft.exchange.core.model.EntitySelections;
import pl.streamsoft.exchange.core.model.ExchangeEntity;
import pl.streamsoft.exchange.core.util.UtilLogger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

//TODO Use selections manager to retrieve selections from last user session.
public class SelectionsManager {

	private final List<ExchangeEntity> selectedEntities;

	private static SelectionsManager INSTANCE;

	private SelectionsManager() {
		selectedEntities = new LinkedList<ExchangeEntity>();
	}

	public static SelectionsManager inst() {
		return INSTANCE == null ? INSTANCE = new SelectionsManager() : INSTANCE;
	}

	public static void storeSelections(List<ExchangeEntity> selectedEntities) {
		EntitySelections selections = new EntitySelections();
		for (ExchangeEntity e : selectedEntities) {
			selections.getSelectionIds().add(e.getId());
		}
		CRUD.inst().deleteAll(EntitySelections.class, EntitySelections.NAME);
		CRUD.inst().store(selections);

	}

	public static List<EntitySelections> loadSelections() {
		try {
			Query<EntitySelections> query = Ebean
					.createQuery(EntitySelections.class);
			return query.findList();
		} catch (Exception e) {
			UtilLogger.logError(e.getMessage());
		}
		return new ArrayList<EntitySelections>();
	}

	/**
	 * @return the selectedEntities
	 */
	public List<ExchangeEntity> getSelectedEntities() {
		return selectedEntities;
	}

	public void remove(ExchangeEntity exchangeEntity) {
		getSelectedEntities().remove(exchangeEntity);
	}

	public void add(ExchangeEntity exchangeEntity) {
		getSelectedEntities().add(exchangeEntity);
	}
}
