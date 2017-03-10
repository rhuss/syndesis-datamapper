
package com.mediadriver.atlas.mock.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JavaEnumFields complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JavaEnumFields"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="JavaEnumField" type="{http://mediadriver.com/atlas/mock/v2}JavaEnumField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JavaEnumFields", propOrder = {
    "javaEnumField"
})
public class JavaEnumFields implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "JavaEnumField")
    protected List<JavaEnumField> javaEnumField;

    /**
     * Gets the value of the javaEnumField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the javaEnumField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJavaEnumField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JavaEnumField }
     * 
     * 
     */
    public List<JavaEnumField> getJavaEnumField() {
        if (javaEnumField == null) {
            javaEnumField = new ArrayList<JavaEnumField>();
        }
        return this.javaEnumField;
    }

    public boolean equals(Object object) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final JavaEnumFields that = ((JavaEnumFields) object);
        {
            List<JavaEnumField> leftJavaEnumField;
            leftJavaEnumField = (((this.javaEnumField!= null)&&(!this.javaEnumField.isEmpty()))?this.getJavaEnumField():null);
            List<JavaEnumField> rightJavaEnumField;
            rightJavaEnumField = (((that.javaEnumField!= null)&&(!that.javaEnumField.isEmpty()))?that.getJavaEnumField():null);
            if ((this.javaEnumField!= null)&&(!this.javaEnumField.isEmpty())) {
                if ((that.javaEnumField!= null)&&(!that.javaEnumField.isEmpty())) {
                    if (!leftJavaEnumField.equals(rightJavaEnumField)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if ((that.javaEnumField!= null)&&(!that.javaEnumField.isEmpty())) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int currentHashCode = 1;
        {
            currentHashCode = (currentHashCode* 31);
            List<JavaEnumField> theJavaEnumField;
            theJavaEnumField = (((this.javaEnumField!= null)&&(!this.javaEnumField.isEmpty()))?this.getJavaEnumField():null);
            if ((this.javaEnumField!= null)&&(!this.javaEnumField.isEmpty())) {
                currentHashCode += theJavaEnumField.hashCode();
            }
        }
        return currentHashCode;
    }

}
