
package com.mycompany.ws_conversor_linux_maven;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mycompany.ws_conversor_linux_maven package. 
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

    private final static QName _Pdf2Response_QNAME = new QName("http://ws_conversor_linux_maven.mycompany.com/", "Pdf2Response");
    private final static QName _Pdf2_QNAME = new QName("http://ws_conversor_linux_maven.mycompany.com/", "Pdf2");
    private final static QName _Docx2_QNAME = new QName("http://ws_conversor_linux_maven.mycompany.com/", "docx2");
    private final static QName _Docx2Response_QNAME = new QName("http://ws_conversor_linux_maven.mycompany.com/", "docx2Response");
    private final static QName _Pdf2Pdf_QNAME = new QName("", "pdf");
    private final static QName _Docx2ResponseReturn_QNAME = new QName("", "return");
    private final static QName _Docx2Docx_QNAME = new QName("", "docx");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mycompany.ws_conversor_linux_maven
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Docx2Response }
     * 
     */
    public Docx2Response createDocx2Response() {
        return new Docx2Response();
    }

    /**
     * Create an instance of {@link Pdf2 }
     * 
     */
    public Pdf2 createPdf2() {
        return new Pdf2();
    }

    /**
     * Create an instance of {@link Docx2 }
     * 
     */
    public Docx2 createDocx2() {
        return new Docx2();
    }

    /**
     * Create an instance of {@link Pdf2Response }
     * 
     */
    public Pdf2Response createPdf2Response() {
        return new Pdf2Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pdf2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_conversor_linux_maven.mycompany.com/", name = "Pdf2Response")
    public JAXBElement<Pdf2Response> createPdf2Response(Pdf2Response value) {
        return new JAXBElement<Pdf2Response>(_Pdf2Response_QNAME, Pdf2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pdf2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_conversor_linux_maven.mycompany.com/", name = "Pdf2")
    public JAXBElement<Pdf2> createPdf2(Pdf2 value) {
        return new JAXBElement<Pdf2>(_Pdf2_QNAME, Pdf2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Docx2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_conversor_linux_maven.mycompany.com/", name = "docx2")
    public JAXBElement<Docx2> createDocx2(Docx2 value) {
        return new JAXBElement<Docx2>(_Docx2_QNAME, Docx2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Docx2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_conversor_linux_maven.mycompany.com/", name = "docx2Response")
    public JAXBElement<Docx2Response> createDocx2Response(Docx2Response value) {
        return new JAXBElement<Docx2Response>(_Docx2Response_QNAME, Docx2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pdf", scope = Pdf2 .class)
    public JAXBElement<byte[]> createPdf2Pdf(byte[] value) {
        return new JAXBElement<byte[]>(_Pdf2Pdf_QNAME, byte[].class, Pdf2 .class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = Docx2Response.class)
    public JAXBElement<byte[]> createDocx2ResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_Docx2ResponseReturn_QNAME, byte[].class, Docx2Response.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "docx", scope = Docx2 .class)
    public JAXBElement<byte[]> createDocx2Docx(byte[] value) {
        return new JAXBElement<byte[]>(_Docx2Docx_QNAME, byte[].class, Docx2 .class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = Pdf2Response.class)
    public JAXBElement<byte[]> createPdf2ResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_Docx2ResponseReturn_QNAME, byte[].class, Pdf2Response.class, ((byte[]) value));
    }

}
