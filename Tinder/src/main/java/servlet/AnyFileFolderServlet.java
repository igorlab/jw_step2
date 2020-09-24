package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnyFileFolderServlet extends HttpServlet {

    private final String _ASSETS_ROOT;

    public AnyFileFolderServlet(String ASSETS_ROOT){
        _ASSETS_ROOT = ASSETS_ROOT;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        try (ServletOutputStream so = resp.getOutputStream()){
            String rqName = req.getRequestURI(); // rqName = /css/style.css
            Path rqFilePaths = Paths.get(_ASSETS_ROOT, rqName); // ==> rqFilePaths = assets\css\style.css
            Files.copy(rqFilePaths, so);
//            System.out.println("rqName = " + rqName + "; rqFilePaths = " + rqFilePaths);
        }
    }
}
