package service;

import model.User_info;
import sql.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class DBservice {

    public Iterable<User_info> get_liked(Long uid_who_see) {
        return DBConnect.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "select u.uid, u.name, u.photo from users u, actions a where u.uid = a.uid_whom and a.what = true and a.uid_who = ?"
                );
                stmt.setLong (1, uid_who_see);

                ResultSet rset = stmt.executeQuery();
                ArrayList<User_info> opll = new ArrayList<>();
                while (rset.next()) {
                    Long uid = rset.getLong("uid");
                    String name = rset.getString("name");
                    String photo = rset.getString("photo");
                    User_info opl = new User_info(uid, name, photo);
                    opll.add(opl);
                }
                return Optional.of(opll);
            } catch (SQLException throwables) {

                return Optional.empty();
            }
        }).orElse(new ArrayList<>()); // Optional.getOrElse()
    }

    public void otlikatUser(User_info ui) {
        DBConnect.get().map(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                 "insert into actions (uid_who, uid_whom, what) values ( ?, ?, ?)"
                );
                stmt.setLong(1, ui._uid_whom);
                stmt.setLong(2, ui._uid);
                stmt.setBoolean(3, ui._isLike);
                stmt.execute();
            } catch (SQLException ex) {
                throw new RuntimeException(ex) ;
            }
            return conn;
        }).map(conn -> "Has written successfully")
                .orElse("Connection Error");
    }

    public Optional<Long> login(String name, String passw) {
        return DBConnect.get().map(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                 "select COALESCE((select u.uid from users u where u.name = ? and u.password = ?), -1) as uid;"
                );
                stmt.setString(1, name);
                stmt.setString(2, passw);

                ResultSet rset = stmt.executeQuery();
                Long uid = -1L;
                while (rset.next()) {
                    uid = rset.getLong("uid");
                    System.out.println("uid " + uid);
                }
                return uid;
            } catch (SQLException ex) {
                throw new RuntimeException(ex) ;
            }
        })/*.map(conn -> -1L).orElse(-1L)*/;

    }

    public Optional<User_info> getNewUser(Long uid_who_see) {
        return DBConnect.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "select uid, name, photo from users u where u.uid <> ? and u.uid not in (select uid_whom from actions where uid_who = ?)"
                );
                stmt.setLong (1, uid_who_see);
                stmt.setLong (2, uid_who_see);

                ResultSet rset = stmt.executeQuery();

                User_info opl = null;
                while (rset.next()){
                    Long uid = rset.getLong("uid");
                    String name = rset.getString("name");
                    String photo_url = rset.getString("photo");
                    opl = new User_info(uid, name, photo_url);
                    opl._uid_whom = rset.getLong("uid");
                }
                return Optional.ofNullable(opl);
                
            } catch (SQLException throwables) {
                return Optional.empty();
            }
        }); // Optional.getOrElse()
    }
}
