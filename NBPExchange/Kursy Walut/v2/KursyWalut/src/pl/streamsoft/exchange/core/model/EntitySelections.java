package pl.streamsoft.exchange.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = EntitySelections.NAME)
public class EntitySelections {
	@Transient
	public static final String NAME = "ENTITY_SELECTIONS";
	@Id
	private Integer id;

	private String name;

	private List<Integer> selectionIds;

	public EntitySelections() {
		selectionIds = new ArrayList<Integer>(5);
	}

	/**
	 * @param selectionIds
	 *            the selectionIds to set
	 */
	public void setSelectionIds(List<Integer> selectionIds) {
		this.selectionIds = selectionIds;
	}

	/**
	 * @return the selectionIds
	 */
	public List<Integer> getSelectionIds() {
		return selectionIds;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
