package pl.streamsoft.exchange.core.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EXCHANGES")
public class ExchangeTable {

	private static final int LIST_SIZE = 170;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<ExchangeEntity> entites;

	/**
	 * @uml.property name="publicationDate"
	 */
	@Id
	private Date publicationDate;

	public ExchangeTable() {
		entites = new ArrayList<ExchangeEntity>(LIST_SIZE);
		setCurrentDate();
	}

	public ExchangeTable(String date) {
		entites = new ArrayList<ExchangeEntity>(LIST_SIZE);
		calculateDate(date);
	}

	private void calculateDate(String date) {
		try {
			if (date != null) {
				publicationDate = SimpleDateFormat.getDateInstance()
						.parse(date);
			} else {
				setCurrentDate();
			}
		} catch (ParseException e) {
			setCurrentDate();
			e.printStackTrace();
		}
	}

	public List<ExchangeEntity> getEntites() {
		return entites;
	}

	/**
	 * Getter of the property <tt>publicationDate</tt>
	 * 
	 * @return Returns the publicationDate.
	 * @uml.property name="publicationDate"
	 */
	public Date getPublicationDate() {
		return publicationDate;
	};

	private void setCurrentDate() {
		publicationDate = new Date();
	}

	public void setEntites(List<ExchangeEntity> entites) {
		this.entites = entites;
	}

	/**
	 * Setter of the property <tt>publicationDate</tt>
	 * 
	 * @param publicationDate
	 *            The publicationDate to set.
	 * @uml.property name="publicationDate"
	 */
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}
