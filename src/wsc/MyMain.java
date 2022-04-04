package wsc;

import com.sforce.soap.partner.Connector;

public class MyMain {
//	com.sforce.soap.partner.SoapBindingStub stub = null;

	public static void main(String[] args) {
	    try {
	        MyMain helper = new MyMain();
	        helper.queryContact();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println(ex); 
	    }
	}

	public MyMain() throws Exception {
	    com.sforce.soap.partner.Soap soap = new SoapProxy();
	    stub = new com.sforce.soap.partner.SoapBindingStub ();
	    com.sforce.soap.partner.LoginResult loginResult = soap.login("zzz@zzz.com", "zzzzzzasHO9zzbYIoDH5kaujICoGBA");
	    stub._setProperty(SoapBindingStub.ENDPOINT_ADDRESS_PROPERTY, loginResult.getServerUrl());
	    stub.setHeader("urn:enterprise.soap.sforce.com", "SessionHeader", new SessionHeader(loginResult.getSessionId()));

	    //com.sforce.soap.partner.QueryResult qResult
	    System.out.println(loginResult);             
	}

	public void queryContact() throws Exception {
	    String soqlQuery = "SELECT FirstName, LastName FROM Contact limit 1";
	    QueryResult qr = stub.query(soqlQuery);
	    SObject[] recordList = qr.getRecords();
	    for (int i = 0; i < recordList.length; i++) {
	        SObject contact = recordList[i];
	        //Object firstName = contact.getField("FirstName");
	        //Object lastName = contact.getField("LastName");   
	        //System.out.println(firstName + " " + lastName);
	        System.out.println(contact);
	    }
	}
}