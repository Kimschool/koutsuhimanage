package koutsuhi.login.dao;

import koutsuhi.data.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean login(String id, String password) {

        DbConn dc = new DbConn();
        Connection cn = dc.connection();
        boolean login_success;


        try {
            String sql = "select * from userinfo where id=? and password=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            login_success = rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return login_success;

    }

}
