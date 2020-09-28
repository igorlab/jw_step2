package util;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class Cookies_helper {
    public static void main(String[] args) {

    }
    public static Optional<Long> GetCookiesByNameLong(Cookie[] cookie, String name){
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (name.equals(c.getName())) {
                    return Optional.of(Long.parseLong(c.getValue()));
                }
            }
        }
        return Optional.empty();
    }
}
