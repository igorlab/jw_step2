package servlet;

import model.User_info;
import service.DBservice;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

public class TestServlet extends HttpServlet {
    private final DBservice _connection;

    public TestServlet(DBservice dataFromDB) {
        _connection = dataFromDB;
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        final Optional<Integer> a = Params.getIntParam("a", req);
//        final Optional<Integer> b = Params.getIntParam("b", req);
//        final Optional<CalcOperation> ops = parseOp(Params.getStrParam("operation", req));
//
//        Optional<String> result =
//                a.flatMap(ai ->
//                        b.flatMap(bi ->
//                                ops.map((CalcOperation op) -> {
//                                    int r = op.doOp(ai, bi);
//                                    storage.log(new OperationLog(ai, bi, op.show(), r));
//                                    return represent(ai, bi, op.show(), r);
//                                })
//                        )
//                );
//        try (PrintWriter w = resp.getWriter()) {
//            w.println(result.orElse("Missed data"));
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String isLike = req.getParameter("isLike");// Params.getIntParam("a", req);
        final String id = req.getParameter("id");// Params.getIntParam("a", req);
        User_info user_info = new User_info(123L, "qwer", "wertyu");

        user_info._uid_whom = Long.parseLong(id);
        user_info._isLike = Boolean.parseBoolean(isLike);
        System.out.println(user_info);

        _connection.otlikatUser(user_info);

//        final Optional<CalcOperation> ops = parseOp(Params.getStrParam("operation", req));
//
//        Optional<String> result =
//                a.flatMap(ai ->
//                        b.flatMap(bi ->
//                                ops.map((CalcOperation op) -> {
//                                    int r = op.doOp(ai, bi);
//                                    storage.log(new OperationLog(ai, bi, op.show(), r));
//                                    return represent(ai, bi, op.show(), r);
//                                })
//                        )
//                );
//        try (PrintWriter w = resp.getWriter()) {
//            w.println(result.orElse("Missed data"));
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngine.folder("assets");
        User_info user_photos = _connection.get();

        System.out.println(user_photos);

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", user_photos._name);
        data.put("photo", user_photos._url_photo);
        data.put("uid", user_photos._uid);

        engine.render(Paths.get("like-page.ftl").toString(), data, resp);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (PrintWriter w = resp.getWriter()){
//            w.println("<b>Hello world</b>");
//        try (ServletOutputStream os = resp.getOutputStream()) {
//            TemplateEngine engine = TemplateEngine.folder("assets");
//            User_photo user_photos = _connection.get();
//
//            System.out.println(user_photos);
//
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("name", user_photos._name);
//            data.put("photo", user_photos._url_photo);
//            data.put("uid", user_photos._uid);
//            Files.copy(Paths.get("assets/like-page.ftl"), os);
//        }


//    }
}
