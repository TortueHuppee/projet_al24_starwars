
package fr.afcepf.al24.bank.validation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.al24.bank.validation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValiderCarteResponse_QNAME = new QName("http://validation.bank.al24.afcepf.fr/", "validerCarteResponse");
    private final static QName _ValiderCarte_QNAME = new QName("http://validation.bank.al24.afcepf.fr/", "validerCarte");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.al24.bank.validation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValiderCarte }
     * 
     */
    public ValiderCarte createValiderCarte() {
        return new ValiderCarte();
    }

    /**
     * Create an instance of {@link ValiderCarteResponse }
     * 
     */
    public ValiderCarteResponse createValiderCarteResponse() {
        return new ValiderCarteResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValiderCarteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://validation.bank.al24.afcepf.fr/", name = "validerCarteResponse")
    public JAXBElement<ValiderCarteResponse> createValiderCarteResponse(ValiderCarteResponse value) {
        return new JAXBElement<ValiderCarteResponse>(_ValiderCarteResponse_QNAME, ValiderCarteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValiderCarte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://validation.bank.al24.afcepf.fr/", name = "validerCarte")
    public JAXBElement<ValiderCarte> createValiderCarte(ValiderCarte value) {
        return new JAXBElement<ValiderCarte>(_ValiderCarte_QNAME, ValiderCarte.class, null, value);
    }

}
