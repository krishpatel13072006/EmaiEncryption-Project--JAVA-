package dao;

import Model.User;
import db.Myconnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//user data accessing operation
public class UserDAO {

    public static boolean isexist(String email)throws SQLException
    {
        Connection connection = Myconnections.getConnection();
        PreparedStatement pre=connection.prepareStatement("select email from users");
        ResultSet rs=pre.executeQuery();

        while(rs.next())
        {
            String e =rs.getString(1);
            if(e.equals(email))
            {
                return true;
            }
        }
        return false;
    }

    public static int save(User user)throws SQLException{
        Connection con=Myconnections.getConnection();
        PreparedStatement pre = con.prepareStatement("insert into users values(default,?,?)");
        pre.setString(1,user.getname());
        pre.setString(2,user.getemail());

        return  pre.executeUpdate();

    }

}
