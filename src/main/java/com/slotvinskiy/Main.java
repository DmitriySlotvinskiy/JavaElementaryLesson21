package com.slotvinskiy;

import com.slotvinskiy.dao.UsersDao;
import com.slotvinskiy.model.User;

//- void removeAll()
//- void removeUser(int id)
//- void removeUserByName(String name)
//- void addUser(User user)
//- void updateUser(User user)
//- User getUser(int id)
//- List<User> getAllUsers()

public class Main {

    public static void main(String[] args) {

        UsersDao usersDao = new UsersDao();
        usersDao.removeAll();

        usersDao.addUser(new User("Linn", 33));
        usersDao.addUser(new User("Sergio", 45));
        usersDao.addUser(new User("Ada", 25));
        usersDao.addUser(new User("John Dou", 0));
        usersDao.addUser(new User("Jane Dou", 0));
        usersDao.addUser(new User("Ben", 30));

        String userName = "Sergio";
        User user1 = usersDao.getUserByName(userName);
        if (user1 != null) {
            System.out.println(user1);
        } else {
            System.out.println("There is not User with name " + userName);
        }

        User user2 = usersDao.getUser(227);
        if (user2 != null) {
            user2.setAge(88);
            usersDao.updateUser(user2);
        }
        System.out.println("User list: " + usersDao.getAllUsers());
        usersDao.removeUser(210);
        usersDao.removeUserByName("Linn");
        System.out.println("User list: " + usersDao.getAllUsers());
        System.out.println("User list with age from 10 - 40 years: " + usersDao.getByAge(10, 40));
        usersDao.close();
    }
}

