
package com.mediadriver.atlas.mock.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mediadriver.atlas.v2.Document;


/**
 * <p>Java class for JavaClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JavaClass"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://mediadriver.com/atlas/v2}Document"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="JavaEnumFields" type="{http://mediadriver.com/atlas/mock/v2}JavaEnumFields"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="className" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="packageName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="fullyQualifiedName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="annotation" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="annonymous" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="array" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="enumeration" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="interface" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="localClass" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="memberClass" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="primitive" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="synthetic" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JavaClass", propOrder = {
    "javaEnumFields"
})
@XmlRootElement(name = "JavaClass")
@JsonRootName("JavaClass")
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "jsonType")
public class JavaClass
    extends Document
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "JavaEnumFields", required = true)
    protected JavaEnumFields javaEnumFields;
    @XmlAttribute(name = "className")
    protected String className;
    @XmlAttribute(name = "packageName")
    protected String packageName;
    @XmlAttribute(name = "fullyQualifiedName")
    protected String fullyQualifiedName;
    @XmlAttribute(name = "annotation")
    protected Boolean annotation;
    @XmlAttribute(name = "annonymous")
    protected Boolean annonymous;
    @XmlAttribute(name = "array")
    protected Boolean array;
    @XmlAttribute(name = "enumeration")
    protected Boolean enumeration;
    @XmlAttribute(name = "interface")
    protected Boolean _interface;
    @XmlAttribute(name = "localClass")
    protected Boolean localClass;
    @XmlAttribute(name = "memberClass")
    protected Boolean memberClass;
    @XmlAttribute(name = "primitive")
    protected Boolean primitive;
    @XmlAttribute(name = "synthetic")
    protected Boolean synthetic;

    /**
     * Gets the value of the javaEnumFields property.
     * 
     * @return
     *     possible object is
     *     {@link JavaEnumFields }
     *     
     */
    public JavaEnumFields getJavaEnumFields() {
        return javaEnumFields;
    }

    /**
     * Sets the value of the javaEnumFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link JavaEnumFields }
     *     
     */
    public void setJavaEnumFields(JavaEnumFields value) {
        this.javaEnumFields = value;
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
     * Gets the value of the packageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the value of the packageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageName(String value) {
        this.packageName = value;
    }

    /**
     * Gets the value of the fullyQualifiedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    /**
     * Sets the value of the fullyQualifiedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullyQualifiedName(String value) {
        this.fullyQualifiedName = value;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAnnotation(Boolean value) {
        this.annotation = value;
    }

    /**
     * Gets the value of the annonymous property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAnnonymous() {
        return annonymous;
    }

    /**
     * Sets the value of the annonymous property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAnnonymous(Boolean value) {
        this.annonymous = value;
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
     * Gets the value of the enumeration property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnumeration() {
        return enumeration;
    }

    /**
     * Sets the value of the enumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnumeration(Boolean value) {
        this.enumeration = value;
    }

    /**
     * Gets the value of the interface property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInterface() {
        return _interface;
    }

    /**
     * Sets the value of the interface property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInterface(Boolean value) {
        this._interface = value;
    }

    /**
     * Gets the value of the localClass property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLocalClass() {
        return localClass;
    }

    /**
     * Sets the value of the localClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLocalClass(Boolean value) {
        this.localClass = value;
    }

    /**
     * Gets the value of the memberClass property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMemberClass() {
        return memberClass;
    }

    /**
     * Sets the value of the memberClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMemberClass(Boolean value) {
        this.memberClass = value;
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
        final JavaClass that = ((JavaClass) object);
        {
            JavaEnumFields leftJavaEnumFields;
            leftJavaEnumFields = this.getJavaEnumFields();
            JavaEnumFields rightJavaEnumFields;
            rightJavaEnumFields = that.getJavaEnumFields();
            if (this.javaEnumFields!= null) {
                if (that.javaEnumFields!= null) {
                    if (!leftJavaEnumFields.equals(rightJavaEnumFields)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.javaEnumFields!= null) {
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
            String leftPackageName;
            leftPackageName = this.getPackageName();
            String rightPackageName;
            rightPackageName = that.getPackageName();
            if (this.packageName!= null) {
                if (that.packageName!= null) {
                    if (!leftPackageName.equals(rightPackageName)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.packageName!= null) {
                    return false;
                }
            }
        }
        {
            String leftFullyQualifiedName;
            leftFullyQualifiedName = this.getFullyQualifiedName();
            String rightFullyQualifiedName;
            rightFullyQualifiedName = that.getFullyQualifiedName();
            if (this.fullyQualifiedName!= null) {
                if (that.fullyQualifiedName!= null) {
                    if (!leftFullyQualifiedName.equals(rightFullyQualifiedName)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.fullyQualifiedName!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftAnnotation;
            leftAnnotation = this.isAnnotation();
            Boolean rightAnnotation;
            rightAnnotation = that.isAnnotation();
            if (this.annotation!= null) {
                if (that.annotation!= null) {
                    if (!leftAnnotation.equals(rightAnnotation)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.annotation!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftAnnonymous;
            leftAnnonymous = this.isAnnonymous();
            Boolean rightAnnonymous;
            rightAnnonymous = that.isAnnonymous();
            if (this.annonymous!= null) {
                if (that.annonymous!= null) {
                    if (!leftAnnonymous.equals(rightAnnonymous)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.annonymous!= null) {
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
            Boolean leftEnumeration;
            leftEnumeration = this.isEnumeration();
            Boolean rightEnumeration;
            rightEnumeration = that.isEnumeration();
            if (this.enumeration!= null) {
                if (that.enumeration!= null) {
                    if (!leftEnumeration.equals(rightEnumeration)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.enumeration!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftInterface;
            leftInterface = this.isInterface();
            Boolean rightInterface;
            rightInterface = that.isInterface();
            if (this._interface!= null) {
                if (that._interface!= null) {
                    if (!leftInterface.equals(rightInterface)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that._interface!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftLocalClass;
            leftLocalClass = this.isLocalClass();
            Boolean rightLocalClass;
            rightLocalClass = that.isLocalClass();
            if (this.localClass!= null) {
                if (that.localClass!= null) {
                    if (!leftLocalClass.equals(rightLocalClass)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.localClass!= null) {
                    return false;
                }
            }
        }
        {
            Boolean leftMemberClass;
            leftMemberClass = this.isMemberClass();
            Boolean rightMemberClass;
            rightMemberClass = that.isMemberClass();
            if (this.memberClass!= null) {
                if (that.memberClass!= null) {
                    if (!leftMemberClass.equals(rightMemberClass)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.memberClass!= null) {
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
            JavaEnumFields theJavaEnumFields;
            theJavaEnumFields = this.getJavaEnumFields();
            if (this.javaEnumFields!= null) {
                currentHashCode += theJavaEnumFields.hashCode();
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
            String thePackageName;
            thePackageName = this.getPackageName();
            if (this.packageName!= null) {
                currentHashCode += thePackageName.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theFullyQualifiedName;
            theFullyQualifiedName = this.getFullyQualifiedName();
            if (this.fullyQualifiedName!= null) {
                currentHashCode += theFullyQualifiedName.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theAnnotation;
            theAnnotation = this.isAnnotation();
            if (this.annotation!= null) {
                currentHashCode += theAnnotation.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theAnnonymous;
            theAnnonymous = this.isAnnonymous();
            if (this.annonymous!= null) {
                currentHashCode += theAnnonymous.hashCode();
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
            Boolean theEnumeration;
            theEnumeration = this.isEnumeration();
            if (this.enumeration!= null) {
                currentHashCode += theEnumeration.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theInterface;
            theInterface = this.isInterface();
            if (this._interface!= null) {
                currentHashCode += theInterface.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theLocalClass;
            theLocalClass = this.isLocalClass();
            if (this.localClass!= null) {
                currentHashCode += theLocalClass.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theMemberClass;
            theMemberClass = this.isMemberClass();
            if (this.memberClass!= null) {
                currentHashCode += theMemberClass.hashCode();
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
            Boolean theSynthetic;
            theSynthetic = this.isSynthetic();
            if (this.synthetic!= null) {
                currentHashCode += theSynthetic.hashCode();
            }
        }
        return currentHashCode;
    }

}
