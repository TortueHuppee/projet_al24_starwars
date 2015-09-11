
package fr.afcepf.al24.bank.validation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for validerCarte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validerCarte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroCarte" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateFinValidite" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cryptogramme" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validerCarte", propOrder = {
    "numeroCarte",
    "dateFinValidite",
    "cryptogramme"
})
public class ValiderCarte {

    protected Long numeroCarte;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFinValidite;
    protected Integer cryptogramme;

    /**
     * Gets the value of the numeroCarte property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroCarte() {
        return numeroCarte;
    }

    /**
     * Sets the value of the numeroCarte property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroCarte(Long value) {
        this.numeroCarte = value;
    }

    /**
     * Gets the value of the dateFinValidite property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFinValidite() {
        return dateFinValidite;
    }

    /**
     * Sets the value of the dateFinValidite property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFinValidite(XMLGregorianCalendar value) {
        this.dateFinValidite = value;
    }

    /**
     * Gets the value of the cryptogramme property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCryptogramme() {
        return cryptogramme;
    }

    /**
     * Sets the value of the cryptogramme property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCryptogramme(Integer value) {
        this.cryptogramme = value;
    }

}
