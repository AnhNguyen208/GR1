package org.example.service;

import org.example.db.ConnectDB;
import org.example.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public String checkUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        // System.out.println(sql);
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {
            // System.out.println("Email: " + res.getString("email") + " Password: " + res.getString("password"));
            if(!email.equals(res.getString("email"))) {
                return "FAILED";
            } else {
                if (!password.equals(res.getString("password")) ) {
                    return "FAILED";
                } else {
                    User user = new User();
                    user.setId(res.getLong("id"));
                    user.setEmail(email);
                    user.setName(res.getString("name"));
                    user.setPhoneNumber(res.getString("phone_number"));
                    user.setDob(res.getDate("dob"));

                    return "SUCCESS|" + user.toString();
                }
            }
        }
        return "FAILED";
    }

    public void updateUser(Long id, String email, String name, String phoneNumber, String dob) throws SQLException {
        String sql = "UPDATE users " +
                "SET email = '" + email + "', " +
                "name = '" + name + "', " +
                "phone_number = '" + phoneNumber + "', " +
                "dob = '" + dob + "' " +
                " WHERE id = " + id + "";
        System.out.println(sql);
        Statement stm = ConnectDB.getConnection().createStatement();
        stm.executeUpdate(sql);
    }

    public String numberFriend(Long id) throws SQLException {
        String msg = "NUMBER_FRIEND" + "|";
        List<Long> idList = new ArrayList<>();
        Integer num = 0;
        String sql = "SELECT * FROM friends where id_user1 = " + id + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            num++;
            idList.add(res.getLong("id_user2"));
        }
        msg += num + "|";
        for (Long i: idList) {
            msg += i + "|";
        }
        return msg;
    }

    public User findUserById(Long id) throws SQLException {
        User user = new User();
        String sql = "SELECT * FROM users where id = " + id + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            user.setId(res.getLong("id"));
            user.setName(res.getString("name"));
            user.setEmail(res.getString("email"));
            user.setPhoneNumber(res.getString("phone_number"));
            user.setDob(res.getDate("dob"));
        }
        return user;
    }

    public String numberUser() throws SQLException {
        String msg = "NUMBER_USER" + "|";
        List<Long> idList = new ArrayList<>();
        Integer num = 0;
        String sql = "SELECT * FROM users";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            num++;
            idList.add(res.getLong("id"));
        }
        msg += num + "|";
        for (Long i: idList) {
            msg += i + "|";
        }
        return msg;
    }
}
