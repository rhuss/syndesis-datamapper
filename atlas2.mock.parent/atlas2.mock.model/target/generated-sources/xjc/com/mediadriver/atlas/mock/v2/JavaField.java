
package com.mediadriver.atlas.mock.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mediadriver.atlas.v2.Field;
import com.mediadriver.atlas.v2.FieldType;
import com.mediadriver.atlas.v2.StringList;


/**
 * <p>Java class for JavaField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JavaField"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://mediadriver.com/atlas/v2}Field"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Annotations" type="{http://mediadriver.com/atlas/v2}StringList"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="className" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="type" type="{http://mediadriver.com/atlas/v2}FieldType" /&gt;
 *       &lt;attribute name="getMethod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="setMethod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="primitive" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="array" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="synthetic" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JavaField", propOrder = {
    "annotations"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "jsonType")
public class JavaField
    extends Field
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Annotations", required = true)
    protected StringList annotations;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "value")
    protected String value;
    @XmlAttribute(name = "className")
    protected String className;
    @XmlAttribute(name = "type")
    protected FieldType type;
    @XmlAttribute(name = "getMethod")
    protected String getMethod;
    @XmlAttribute(name = "setMethod")
    protected String setMethod;
    @XmlAttribute(name = "primitive")
    protected Boolean primitive;
    @XmlAttribute(name = "array")
    protected Boolean array;
    @XmlAttribute(name = "synthetic")
    protected Boolean synthetic;

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link StringList }
     *     
     */
    public StringList getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringList }
     *     
     */
    public void setAnnotations(StringList value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link FieldType }
     *     
     */
    public FieldType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldType }
     *     
     */
    public void setType(FieldType value) {
        this.type = value;
    }

    /**
     * Gets the value of the getMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetMethod() {
        return getMethod;
    }

    /**
     * Sets the value of the getMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetMethod(String value) {
        this.getMethod = value;
    }

    /**
     * Gets the value of the setMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetMethod() {
        return setMethod;
    }

    /**
     * Sets the value of the setMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetMethod(String value) {
        this.setMethod = value;
    }

    /**
     * Gets the value of the primitive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrimitive() {
        return primitive;
    }

    /**
     * Sets the value of the primitive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrimitive(Boolean value) {
        this.primitive = value;
    }

    /**
     * Gets the value of the array property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isArray() {
        return array;
    }

    /**
     * Sets the value of the array property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setArray(Boolean value) {
        this.array = value;
    }

    /**
     * Gets the value of the synthetic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSynthetic() {
        return synthetic;
    }

    /**
     * Sets the value of the synthetic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSynthetic(Boolean value) {
        this.synthetic = value;
    }

    public boolean equals(Object object) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(object)) {
            return false;
        }
        final JavaField that = ((JavaField) object);
        {
            StringList leftAnnotations;
            leftAnnotations = this.getAnnotations();
            StringList rightAnnotations;
            rightAnnotations = that.getAnnotations();
            if (this.annotations!= null) {
                if (that.annotations!= null) {
                    if (!leftAnnotations.equals(rightAnnotations)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.annotations!= null) {
                    return false;
                }
            }
        }
        {
            String leftName;
            leftName = this.getName();
            String rightName;
            rightName = that.getName();
            if (this.name!= null) {
                if (that.name!= null) {
                    if (!leftName.equals(rightName)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.name!= null) {
                    return false;
                }
            }
        }
        {
            String leftValue;
            leftValue = this.getValue();
            String rightValue;
            rightValue = that.getValue();
            if (this.value!= null) {
                if (that.value!= null) {
                    if (!leftValue.equals(rightValue)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.value!= null) {
                    return false;
                }
            }
        }
        {
            String leftClassName;
            leftClassName = this.getClassName();
            String rightClassName;
            rightClassName = that.getClassName();
            if (this.className!= null) {
                if (that.className!= null) {
                    if (!leftClassName.equals(rightClassName)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.className!= null) {
                    return false;
                }
            }
        }
        {
            FieldType leftType;
            leftType = this.getType();
            FieldType rightType;
            rightType = that.getType();
            if (this.type!= null) {
                if (that.type!= null) {
                    if (!leftType.equals(rightType)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.type!= null) {
                    return false;
                }
            }
        }
        {
            String leftGetMethod;
            leftGetMethod = this.getGetMethod();
            String rightGetMethod;
            rightGetMethod = that.getGetMethod();
            if (this.getMethod!= null) {
                if (that.getMethod!= null) {
                    if (!leftGetMethod.equals(rightGetMethod)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.getMethod!= null) {
                    return false;
                }
            }
        }
        {
            String leftSetMethod;
            leftSetMethod = this.getSetMethod();
            String rightSetMethod;
            rightSetMethod = that.getSetMethod();
            if (this.setMethod!= null) {
                if (that.setMethod!= null) {
                    if (!leftSetMethod.equals(rightSetMethod)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.setMethod!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftPrimitive;
            leftPrimitive = this.isPrimitive();
            Boolean rightPrimitive;
            rightPrimitive = that.isPrimitive();
            if (this.primitive!= null) {
                if (that.primitive!= null) {
                    if (!leftPrimitive.equals(rightPrimitive)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.primitive!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftArray;
            leftArray = this.isArray();
            Boolean rightArray;
            rightArray = that.isArray();
            if (this.array!= null) {
                if (that.array!= null) {
                    if (!leftArray.equals(rightArray)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.array!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftSynthetic;
            leftSynthetic = this.isSynthetic();
            Boolean rightSynthetic;
            rightSynthetic = that.isSynthetic();
            if (this.synthetic!= null) {
                if (that.synthetic!= null) {
                    if (!leftSynthetic.equals(rightSynthetic)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.synthetic!= null) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int currentHashCode = 1;
        currentHashCode = ((currentHashCode* 31)+ super.hashCode());
        {
            currentHashCode = (currentHashCode* 31);
            StringList theAnnotations;
            theAnnotations = this.getAnnotations();
            if (this.annotations!= null) {
                currentHashCode += theAnnotations.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theName;
            theName = this.getName();
            if (this.name!= null) {
                currentHashCode += theName.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theValue;
            theValue = this.getValue();
            if (this.value!= null) {
                currentHashCode += theValue.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theClassName;
            theClassName = this.getClassName();
            if (this.className!= null) {
                currentHashCode += theClassName.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            FieldType theType;
            theType = this.getType();
            if (this.type!= null) {
                currentHashCode += theType.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theGetMethod;
            theGetMethod = this.getGetMethod();
            if (this.getMethod!= null) {
                currentHashCode += theGetMethod.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theSetMethod;
            theSetMethod = this.getSetMethod();
            if (this.setMethod!= null) {
                currentHashCode += theSetMethod.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean thePrimitive;
            thePrimitive = this.isPrimitive();
            if (this.primitive!= null) {
                currentHashCode += thePrimitive.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theArray;
            theArray = this.isArray();
            if (this.array!= null) {
                currentHashCode += theArray.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theSynthetic;
            theSynthetic = this.isSynthetic();
            if (this.synthetic!= null) {
                currentHashCode += theSynthetic.hashCode();
            }
        }
        return currentHashCode;
    }

}
