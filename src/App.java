import javax.xml.ws.Endpoint;

import services.UserAuthentication;
import services.UserManagement;

public class App {
    public static void main(String[] args) throws Exception {
        String url1 = "http://localhost:8080/UserManagement?wsdl";
        String url2 = "http://localhost:8080/UserAuthentication?wsdl";
        Endpoint.publish(url1, new UserManagement());
        Endpoint.publish(url2, new UserAuthentication());
		System.out.println("\nServer For User Management Up and Running at : " + url1);
		System.out.println("\nServer For User Auhtentication Up and Running at : " + url2);
    }
}
