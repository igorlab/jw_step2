import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.DBservice;
import servlet.AnyFileFolderServlet;
import servlet.TestServlet;

public class Tinder {
    public static void main(String[] args) throws Exception {

        DBservice psqlStorage = new DBservice();

        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(
                new TestServlet(psqlStorage)), "/users");

        ServletHolder anyFile = new ServletHolder(new AnyFileFolderServlet("assets"));
        handler.addServlet(anyFile, "/css/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
