package org.example.service;

import org.example.db.ConnectDB;

import java.sql.SQLException;
import java.sql.Statement;

public class FriendService {
    public void unfriend(Long id1, Long id2) throws SQLException {
        String sql = "DELETE FROM friends " +
                " WHERE id_user1 = " + id1 + " && id_user2 = " +id2;
        Statement stm = ConnectDB.getConnection().createStatement();
        stm.executeUpdate(sql);

        sql = "DELETE FROM friends " +
                " WHERE id_user1 = " + id2 + " && id_user2 = " +id1;
        stm = ConnectDB.getConnection().createStatement();
        stm.executeUpdate(sql);
    }

    public void friendRequest(Long id1, Long id2) throws SQLException {
        String sql = "INSERT INTO friend_request " +
                "(`id_user1`, `id_user2`, `status`) VALUES ('"+ id1 +"', '" + id2 + "', '0')";
        Statement stm = ConnectDB.getConnection().createStatement();
        stm.executeUpdate(sql);
    }
}
