package model;

import java.util.Optional;

public class User_info  {
  public final Long _uid;
  public final String _name;
  public final String _url_photo;
  public Long _uid_whom;
  public boolean _isLike;

  public User_info(Long _uid, String _name, String url_photo) {
    this._uid = _uid;
    this._name = _name;
    _url_photo = url_photo;
  }

  public String getuName() {
    return _name;
  }
  public String getuphoto() {
    return _url_photo;
  }

  public String getuID() {
    return _uid.toString();
  }

  @Override
  public String toString() {
    return String.format(
            "[%d %s %s %d %s]", _uid, _name, _url_photo, _uid_whom, _isLike ? "true" : "false");
  }

}
