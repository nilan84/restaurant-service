package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.User;

import java.util.List;

/**
 * Created by nilan on 9/20/15.
 */
public interface UserDAOService {

 public List<User> getAllUser();
 public long addUser(User user);
 public void editUser(User user);
 public User getUserById(int userId);
 public User getUserByName(String userName);

}
