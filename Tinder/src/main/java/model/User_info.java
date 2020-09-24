package model;

public class User_info {
  public final Long _uid;
  public final String _name;
  public final String _url_photo;
  public Long _uid_whom;
  public boolean _isLike;

  public User_info(Long uid, String name, String url_photo) {
    _uid = uid;
    _name = name;
    _url_photo = url_photo;
  }

  @Override
  public String toString() {
    return String.format(
        "[%d %s %s %d %s]", _uid, _name, _url_photo, _uid_whom, _isLike ? "true" : "false");
  }
}
