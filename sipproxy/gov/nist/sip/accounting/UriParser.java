package gov.nist.sip.proxy;

import javax.sip.address.*;

public class UriParser {

	private URI uri;
	
	public UriParser (URI uri) {
		this.uri = uri; 
	}
	
	public String getName() {		
	    String uriString = uri.toString();
	    String name = uriString.substring(uriString.indexOf("sip:") + 4, uriString.indexOf("@"));
	    return name;
	}
}