
package fr.afcepf.al24.bank.transaction;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.al24.bank.transaction package. 
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

    private final static QName _EffectuerTransaction_QNAME = new QName("http://transaction.bank.al24.afcepf.fr/", "effectuerTransaction");
    private final static QName _EffectuerTransactionResponse_QNAME = new QName("http://transaction.bank.al24.afcepf.fr/", "effectuerTransactionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.al24.bank.transaction
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EffectuerTransaction }
     * 
     */
    public EffectuerTransaction createEffectuerTransaction() {
        return new EffectuerTransaction();
    }

    /**
     * Create an instance of {@link EffectuerTransactionResponse }
     * 
     */
    public EffectuerTransactionResponse createEffectuerTransactionResponse() {
        return new EffectuerTransactionResponse();
    }

    /**
     * Create an instance of {@link WsRetourTransaction }
     * 
     */
    public WsRetourTransaction createWsRetourTransaction() {
        return new WsRetourTransaction();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EffectuerTransaction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transaction.bank.al24.afcepf.fr/", name = "effectuerTransaction")
    public JAXBElement<EffectuerTransaction> createEffectuerTransaction(EffectuerTransaction value) {
        return new JAXBElement<EffectuerTransaction>(_EffectuerTransaction_QNAME, EffectuerTransaction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EffectuerTransactionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transaction.bank.al24.afcepf.fr/", name = "effectuerTransactionResponse")
    public JAXBElement<EffectuerTransactionResponse> createEffectuerTransactionResponse(EffectuerTransactionResponse value) {
        return new JAXBElement<EffectuerTransactionResponse>(_EffectuerTransactionResponse_QNAME, EffectuerTransactionResponse.class, null, value);
    }

}
