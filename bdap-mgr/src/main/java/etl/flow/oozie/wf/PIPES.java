//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.03 at 12:23:46 PM PDT 
//


package etl.flow.oozie.wf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PIPES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PIPES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="map" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reduce" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inputformat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partitioner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="writer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="program" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PIPES", propOrder = {
    "map",
    "reduce",
    "inputformat",
    "partitioner",
    "writer",
    "program"
})
public class PIPES {

    protected String map;
    protected String reduce;
    protected String inputformat;
    protected String partitioner;
    protected String writer;
    protected String program;

    /**
     * Gets the value of the map property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMap() {
        return map;
    }

    /**
     * Sets the value of the map property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMap(String value) {
        this.map = value;
    }

    /**
     * Gets the value of the reduce property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReduce() {
        return reduce;
    }

    /**
     * Sets the value of the reduce property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReduce(String value) {
        this.reduce = value;
    }

    /**
     * Gets the value of the inputformat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputformat() {
        return inputformat;
    }

    /**
     * Sets the value of the inputformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputformat(String value) {
        this.inputformat = value;
    }

    /**
     * Gets the value of the partitioner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitioner() {
        return partitioner;
    }

    /**
     * Sets the value of the partitioner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitioner(String value) {
        this.partitioner = value;
    }

    /**
     * Gets the value of the writer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWriter() {
        return writer;
    }

    /**
     * Sets the value of the writer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWriter(String value) {
        this.writer = value;
    }

    /**
     * Gets the value of the program property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgram() {
        return program;
    }

    /**
     * Sets the value of the program property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgram(String value) {
        this.program = value;
    }

}
