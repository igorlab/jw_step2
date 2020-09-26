package servlet;

import model.User_info;
import service.DBservice;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Login extends HttpServlet {
    private final DBservice _connection;
    TemplateEngine engine = TemplateEngine.folder("assets");

    public Login(DBservice dataFromDB) {
        _connection = dataFromDB;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.getWriter();


            final String name = req.getParameter("name");// Params.getIntParam("a", req);
            final String passw = req.getParameter("passw");// Params.getIntParam("a", req);
            Long uid = _connection.login(name, passw).get();
            if (uid != -1) {
                System.out.println("U r logged in");
                Cookie cookie = new Cookie("uid", uid.toString());
                resp.addCookie(cookie);
                resp.sendRedirect("/users");
            } else {
                System.out.println("wrong passw or login");
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            engine.render("login.html", new HashMap<>(), resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
