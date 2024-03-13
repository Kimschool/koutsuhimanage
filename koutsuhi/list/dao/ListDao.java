package koutsuhi.list.dao;

import koutsuhi.data.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListDao {

    public List<String[]> getTransInfoList(String id, String date) {

        DbConn conn = new DbConn();

        Connection cn = conn.connection();
        List<String[]> transInfoList = new ArrayList<>();

        try {
            String sql = "select * from transinfo where user_id=? and date like ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, date +"%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] transInfo = new String[5];
                transInfo[0] = rs.getString("no");
                transInfo[1] = rs.getString("date");
                transInfo[2] = rs.getString("d_station");
                transInfo[3] = rs.getString("a_station");
                transInfo[4] = rs.getString("fare");

                transInfoList.add(transInfo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transInfoList;
    }

}
