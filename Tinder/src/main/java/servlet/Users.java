package servlet;

import model.User_info;
import service.DBservice;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

import static java.lang.Long.parseLong;

public class Users extends HttpServlet {
    private final DBservice _connection;
    private long _uid;

    public Users(DBservice dataFromDB) {
        _connection = dataFromDB;
    }

    @Override // kogda otlaikali
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {


            resp.getWriter();
            long uid = parseLong(req.getParameter("id"));// Params.getIntParam("a", req);
            long uid_whom = parseLong(req.getParameter("uidwhom"));// Params.getIntParam("a", req);
            boolean isLike = Boolean.parseBoolean(req.getParameter("isLike"));// Params.getIntParam("a", req);

            User_info user_info = new User_info(uid, "uname", "uphotourl");

            user_info._uid_whom = uid_whom;
            user_info._isLike = isLike;

            _connection.otlikatUser(user_info);

            //todo insert redirect to
            resp.sendRedirect("/users");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter();
            TemplateEngine engine = TemplateEngine.folder("assets");
            long id_who_see = 1L;

            Cookie[] cookies = req.getCookies();
            Cookie cccc = null;
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("uid".equals(c.getName())) {
                        id_who_see = Long.parseLong(c.getValue());
                    }
                }
            }
            final long aaaa = id_who_see;

            Optional<User_info> user_photos = _connection.get(id_who_see);

            if (user_photos.isPresent()) {
                user_photos.ifPresent(a -> {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", a._name);
                    data.put("photo", a._url_photo);
                    data.put("uid", a._uid);
                    data.put("uidwhom", aaaa);
                    engine.render(Paths.get("like-page.ftl").toString(), data, resp);
                });
            } else {
                resp.sendRedirect("/liked");
            }
            ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
