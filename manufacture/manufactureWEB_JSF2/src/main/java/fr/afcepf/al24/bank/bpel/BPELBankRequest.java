
package fr.afcepf.al24.bank.bpel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroDebit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dateFinDebit" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="cryptoDebit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroCredit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dateFinCredit" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="cryptoCredit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numeroDebit",
    "dateFinDebit",
    "cryptoDebit",
    "numeroCredit",
    "dateFinCredit",
    "cryptoCredit",
    "montant"
})
@XmlRootElement(name = "BPELBankRequest")
public class BPELBankRequest {

    protected long numeroDebit;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFinDebit;
    protected int cryptoDebit;
    protected long numeroCredit;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFinCredit;
    protected int cryptoCredit;
    protected double montant;

    /**
     * Gets the value of the numeroDebit property.
     * 
     */
    public long getNumeroDebit() {
        return numeroDebit;
    }

    /**
     * Sets the value of the numeroDebit property.
     * 
     */
    public void setNumeroDebit(long value) {
        this.numeroDebit = value;
    }

    /**
     * Gets the value of the dateFinDebit property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFinDebit() {
        return dateFinDebit;
    }

    /**
     * Sets the value of the dateFinDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFinDebit(XMLGregorianCalendar value) {
        this.dateFinDebit = value;
    }

    /**
     * Gets the value of the cryptoDebit property.
     * 
     */
    public int getCryptoDebit() {
        return cryptoDebit;
    }

    /**
     * Sets the value of the cryptoDebit property.
     * 
     */
    public void setCryptoDebit(int value) {
        this.cryptoDebit = value;
    }

    /**
     * Gets the value of the numeroCredit property.
     * 
     */
    public long getNumeroCredit() {
        return numeroCredit;
    }

    /**
     * Sets the value of the numeroCredit property.
     * 
     */
    public void setNumeroCredit(long value) {
        this.numeroCredit = value;
    }

    /**
     * Gets the value of the dateFinCredit property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFinCredit() {
        return dateFinCredit;
    }

    /**
     * Sets the value of the dateFinCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFinCredit(XMLGregorianCalendar value) {
        this.dateFinCredit = value;
    }

    /**
     * Gets the value of the cryptoCredit property.
     * 
     */
    public int getCryptoCredit() {
        return cryptoCredit;
    }

    /**
     * Sets the value of the cryptoCredit property.
     * 
     */
    public void setCryptoCredit(int value) {
        this.cryptoCredit = value;
    }

    /**
     * Gets the value of the montant property.
     * 
     */
    public double getMontant() {
        return montant;
    }

    /**
     * Sets the value of the montant property.
     * 
     */
    public void setMontant(double value) {
        this.montant = value;
    }

}
