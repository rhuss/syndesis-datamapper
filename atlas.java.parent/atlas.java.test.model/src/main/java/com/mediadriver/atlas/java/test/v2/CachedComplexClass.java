package com.mediadriver.atlas.java.test.v2;

public class CachedComplexClass {

	private TestOrder testOrder;
	private TestAddress primaryAddress;
	private TestContact primaryContact;
	
	public TestOrder getTestOrder() {
		return testOrder;
	}
	public void setTestOrder(TestOrder testOrder) {
		this.testOrder = testOrder;
	}
	public TestAddress getPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(TestAddress primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	public TestContact getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(TestContact primaryContact) {
		this.primaryContact = primaryContact;
	}
}
