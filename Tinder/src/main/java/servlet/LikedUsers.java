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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LikedUsers extends HttpServlet {
    private final DBservice _connection;

    public LikedUsers(DBservice dataFromDB) {
        _connection = dataFromDB;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String isLike = req.getParameter("isLike");// Params.getIntParam("a", req);
        final String id = req.getParameter("id");// Params.getIntParam("a", req);

        User_info user_info = new User_info(123L, "qwer", "wertyu"); // TODO: 25.09.2020 fix

        user_info._uid_whom = Long.parseLong(id);
        user_info._isLike = Boolean.parseBoolean(isLike);
        System.out.println(user_info);

        _connection.otlikatUser(user_info);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngine.folder("assets");

        Cookie[] cookies = req.getCookies();
        Long id_who_see = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("uid".equals(c.getName())) {
                    id_who_see = Long.parseLong(c.getValue());
                }
            }
        }
        final long aaaa = id_who_see;
        Stream<User_info> stream1 = StreamSupport.stream(
                _connection.get_liked(id_who_see).spliterator(),
                false
        );

        List<User_info> liked_users = stream1.collect(Collectors.toList());

        HashMap<String, Object> input = new HashMap<String, Object>();
        input.put("systems", liked_users);

        engine.render("people-list.ftl", input, resp);
    }

}
