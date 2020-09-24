package service;

import model.User_info;
import sql.DBConnect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DBservice {



    public void otlikatUser(User_info ui) {
        DBConnect.get().map(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        //"INSERT INTO history (a, b, op, r) VALUES (?, ?, ?, ?)");
                 "insert into actions (uid_who, uid_whom, what) values ( ?, ?, ?)"
                );
                stmt.setLong(1, ui._uid);
                stmt.setLong(2, ui._uid_whom);
                stmt.setBoolean(3, ui._isLike);

                stmt.execute();

            } catch (SQLException ex) {
                throw new RuntimeException(ex) ;
            }
            return conn;
        }).map(conn -> "Has written successfully")
                .orElse("Connection Error");
//        System.out.println(result);
    }

    public User_info get() {
        return DBConnect.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                 "select uid, name, photo from users u where u.uid not in (select uid_whom from actions where uid_who = 1) limit 1"
                );

                ResultSet rset = stmt.executeQuery();

                User_info opl = null;
                while (rset.next()){
                    Long uid = rset.getLong("uid");
                    String name = rset.getString("name");
                    String photo_url = rset.getString("photo");
                    opl = new User_info(uid, name, photo_url);
                }
                return Optional.of(opl);
                
            } catch (SQLException throwables) {
                return Optional.empty();
            }
        }).orElse(new User_info(1L,"aaa", "bbb")); // Optional.getOrElse()
    }

}
