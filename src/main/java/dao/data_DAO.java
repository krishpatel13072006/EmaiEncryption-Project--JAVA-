package dao;

import Model.Data;
import db.Myconnections;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class data_DAO {

    public static List<Data> getfiles(String email) throws SQLException {
        Connection con = Myconnections.getConnection();
        PreparedStatement pre = con.prepareStatement("select * from data where email=?");
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery();
        List<Data> files = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String Path = rs.getString(3);
            files.add(new Data(id, name, email,Path));
        }
        return files;

    }

    public static int hidefile(Data file) throws SQLException, IOException {
        Connection con = Myconnections.getConnection();
        PreparedStatement pre = con.prepareStatement("insert into data(name,path,email,bin_data)values(?,?,?,?)");
        pre.setString(1, file.name());
        pre.setString(2, file.path());
        pre.setString(3, file.email());
        File f = new File(file.path());
        FileReader read = new FileReader(f);
        pre.setCharacterStream(4, read, f.length());
        int ans = pre.executeUpdate();
        read.close();
        f.delete();
        return ans;
    }


    public static void unhide(int id)throws SQLException,IOException{
        Connection con=Myconnections.getConnection();
        PreparedStatement ps=con.prepareStatement("select path,bin_data from data where id =?");
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String path =rs.getString("path");
        Clob c=rs.getClob("bin_data");

        Reader r=c.getCharacterStream();
        FileWriter fw=new FileWriter(path);
        int i;
        while ((i = r.read()) != -1){
            fw.write((char)i);
        }
        fw.close();
        ps=con.prepareStatement("delete from data where id=?" );
        ps.setInt(1,id);
        System.out.println("Unhidden Successfully!!");
    }


    }


