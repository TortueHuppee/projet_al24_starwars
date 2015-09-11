
package fr.afcepf.al24.bank.transaction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for effectuerTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="effectuerTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroCarteDebit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateFinValiditeDebit" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cryptogrammeDebit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroCarteCredit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateFinValiditeCredit" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cryptogrammeCredit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "effectuerTransaction", propOrder = {
    "numeroCarteDebit",
    "dateFinValiditeDebit",
    "cryptogrammeDebit",
    "numeroCarteCredit",
    "dateFinValiditeCredit",
    "cryptogrammeCredit",
    "montant"
})
public class EffectuerTransaction {

    protected Long numeroCarteDebit;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFinValiditeDebit;
    protected Integer cryptogrammeDebit;
    protected Long numeroCarteCredit;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFinValiditeCredit;
    protected Integer cryptogrammeCredit;
    protected Double montant;

    /**
     * Gets the value of the numeroCarteDebit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroCarteDebit() {
        return numeroCarteDebit;
    }

    /**
     * Sets the value of the numeroCarteDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroCarteDebit(Long value) {
        this.numeroCarteDebit = value;
    }

    /**
     * Gets the value of the dateFinValiditeDebit property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFinValiditeDebit() {
        return dateFinValiditeDebit;
    }

    /**
     * Sets the value of the dateFinValiditeDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFinValiditeDebit(XMLGregorianCalendar value) {
        this.dateFinValiditeDebit = value;
    }

    /**
     * Gets the value of the cryptogrammeDebit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCryptogrammeDebit() {
        return cryptogrammeDebit;
    }

    /**
     * Sets the value of the cryptogrammeDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCryptogrammeDebit(Integer value) {
        this.cryptogrammeDebit = value;
    }

    /**
     * Gets the value of the numeroCarteCredit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    /**
     * Sets the value of the numeroCarteCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroCarteCredit(Long value) {
        this.numeroCarteCredit = value;
    }

    /**
     * Gets the value of the dateFinValiditeCredit property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFinValiditeCredit() {
        return dateFinValiditeCredit;
    }

    /**
     * Sets the value of the dateFinValiditeCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFinValiditeCredit(XMLGregorianCalendar value) {
        this.dateFinValiditeCredit = value;
    }

    /**
     * Gets the value of the cryptogrammeCredit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCryptogrammeCredit() {
        return cryptogrammeCredit;
    }

    /**
     * Sets the value of the cryptogrammeCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCryptogrammeCredit(Integer value) {
        this.cryptogrammeCredit = value;
    }

    /**
     * Gets the value of the montant property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontant() {
        return montant;
    }

    /**
     * Sets the value of the montant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontant(Double value) {
        this.montant = value;
    }

}
