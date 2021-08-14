package ru.scarletarrow.bootmap.service;

import ru.scarletarrow.bootmap.entity.User;

public interface UserService {
    public Object getAllUsers();
    public void verifyUser(User user);
    public User getUserById(String id);
    public User setUser(User user);
    public User saveUser(User user);
}
