package pl.streamsoft.exchange.core.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY")
public class ExchangeEntity implements Comparable<ExchangeEntity> {

	/**
	 * @uml.property name="conversionRate"
	 */
	private int conversionRate;

	/**
	 * @uml.property name="countryName"
	 */
	private String countryName;

	/**
	 * @uml.property name="currencyCode"
	 */
	private String currencyCode;

	private String currencyName;

	/**
	 * @uml.property name="exchangeRate"
	 */
	private String exchangeRate;

	@Id
	private Integer id;

	public ExchangeEntity() {
	}

	public ExchangeEntity(String name, String code, String country,
			String exchangeRate, int conversionRate) {
		this.currencyName = name;
		this.currencyCode = code;
		this.countryName = country;
		this.exchangeRate = exchangeRate;
		this.conversionRate = conversionRate;
	}

	@Override
	public int compareTo(ExchangeEntity o) {
		int compareToIgnoreCase = currencyName
				.compareToIgnoreCase(o.currencyName);
		return compareToIgnoreCase;
	}

	/**
	 * Getter of the property <tt>conversionRate</tt>
	 * 
	 * @return Returns the conversionRate.
	 * @uml.property name="conversionRate"
	 */
	public int getConversionRate() {
		return conversionRate;
	}

	/**
	 * Getter of the property <tt>name</tt>
	 * 
	 * @return Returns the name.
	 * @uml.property name="countryName"
	 */
	public String getCountryName() {
		if (countryName == null)
			return "";
		return countryName;
	}

	/**
	 * Getter of the property <tt>currencyCode</tt>
	 * 
	 * @return Returns the currencyCode.
	 * @uml.property name="currencyCode"
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Getter of the property <tt>currency</tt>
	 * 
	 * @return Returns the currency.
	 * @uml.property name="currencyName"
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	public String getFixedCurrencyName() {
		if (countryName != null && !countryName.isEmpty())
			return currencyName + " (" + countryName + ")";
		else
			return currencyName;
	}

	/**
	 * Getter of the property <tt>exchangeRate</tt>
	 * 
	 * @return Returns the exchangeRate.
	 * @uml.property name="exchangeRate"
	 */
	public String getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * Setter of the property <tt>conversionRate</tt>
	 * 
	 * @param conversionRate
	 *            The conversionRate to set.
	 * @uml.property name="conversionRate"
	 */
	public void setConversionRate(int conversionRate) {
		this.conversionRate = conversionRate;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * 
	 * @param name
	 *            The name to set.
	 * @uml.property name="countryName"
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Setter of the property <tt>currencyCode</tt>
	 * 
	 * @param currencyCode
	 *            The currencyCode to set.
	 * @uml.property name="currencyCode"
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * Setter of the property <tt>currency</tt>
	 * 
	 * @param currency
	 *            The currency to set.
	 * @uml.property name="currencyName"
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * Setter of the property <tt>exchangeRate</tt>
	 * 
	 * @param exchangeRate
	 *            The exchangeRate to set.
	 * @uml.property name="exchangeRate"
	 */
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "\n\nNAZWA: " + currencyName + "\nKraj: " + countryName
				+ "\nKOD: " + currencyCode + "\n KURS ŒREDNI: " + exchangeRate
				+ "\n PRZELICZNIK: " + conversionRate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
