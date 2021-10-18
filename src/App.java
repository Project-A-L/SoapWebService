import javax.xml.ws.Endpoint;

import service.UserManagement;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/UserManagement?wsdl";
        Endpoint.publish(url, new UserManagement());
		System.out.println("Server Up and Running at : " + url);
    }
}
