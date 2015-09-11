
package fr.afcepf.al24.bank.transaction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsRetourTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsRetourTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateTransaction" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idTransaction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="transactionValide" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsRetourTransaction", propOrder = {
    "dateTransaction",
    "idTransaction",
    "transactionValide"
})
public class WsRetourTransaction {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTransaction;
    protected Integer idTransaction;
    protected boolean transactionValide;

    /**
     * Gets the value of the dateTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTransaction() {
        return dateTransaction;
    }

    /**
     * Sets the value of the dateTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTransaction(XMLGregorianCalendar value) {
        this.dateTransaction = value;
    }

    /**
     * Gets the value of the idTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTransaction() {
        return idTransaction;
    }

    /**
     * Sets the value of the idTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTransaction(Integer value) {
        this.idTransaction = value;
    }

    /**
     * Gets the value of the transactionValide property.
     * 
     */
    public boolean isTransactionValide() {
        return transactionValide;
    }

    /**
     * Sets the value of the transactionValide property.
     * 
     */
    public void setTransactionValide(boolean value) {
        this.transactionValide = value;
    }

}
