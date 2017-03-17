package com.mediadriver.atlas.java.test.v2;

import java.io.Serializable;

public class TestOrder implements Serializable {

	private static final long serialVersionUID = 1798627832357917681L;
	
	private TestContact testContact;
	private TestAddress testAddress;
	
	public TestContact getTestContact() {
		return testContact;
	}
	public void setTestContact(TestContact testContact) {
		this.testContact = testContact;
	}
	public TestAddress getTestAddress() {
		return testAddress;
	}
	public void setTestAddress(TestAddress testAddress) {
		this.testAddress = testAddress;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testAddress == null) ? 0 : testAddress.hashCode());
		result = prime * result + ((testContact == null) ? 0 : testContact.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestOrder other = (TestOrder) obj;
		if (testAddress == null) {
			if (other.testAddress != null)
				return false;
		} else if (!testAddress.equals(other.testAddress))
			return false;
		if (testContact == null) {
			if (other.testContact != null)
				return false;
		} else if (!testContact.equals(other.testContact))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TestOrder [testContact=" + testContact.toString() + ", testAddress=" + testAddress.toString() + "]";
	}
}
