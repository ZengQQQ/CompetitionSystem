package Logical_opt;

//import Logical_opt.SqlDB;

import Logical_opt.Utils.Druid_Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class logical_op {

    private static int fetchDirection;

    //---------------------------------------登录-----------------------------------------------//
    public static  boolean U_login(String UID, String password) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String str = "SELECT * FROM link_team.student_info WHERE UID=? AND  password=?";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, UID);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("用户存在");
                System.out.println("登录成功");

                return true;
            } else {
                System.out.println("登录失败，密码或ID不正确");

                return false;
            }
        } catch (Exception e) {
            System.out.println("查询失败");

            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //-----------------------------------------用户注册---------------------------------------------//

    public static  boolean U_signUp(String UID, String password, String UName, String Student_ID, String Student_Name, String Tel) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String str = "SELECT * FROM link_team.student_info WHERE UID=? ";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, UID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("用户已存在");
                System.out.println("注册失败");
                return false;
            } else {
                String tem = "INSERT INTO link_team.student_info (UID,password,UName,Student_ID,Student_Name,Tel) VALUES (?,?,?,?,?,?) ";
                PreparedStatement pstmt1 = Druid_Utils.getConnection().prepareStatement(tem);
                pstmt1.setString(1, UID);
                pstmt1.setString(2, password);
                pstmt1.setString(3, UName);
                pstmt1.setString(4, Student_ID);
                pstmt1.setString(5, Student_Name);
                pstmt1.setString(6, Tel);
                pstmt1.executeUpdate();
                System.out.println("注册成功");
                return true;
            }
        } catch (Exception e) {
            System.out.println("注册失败");
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------信息补全------------------------------------------------//
    public static  boolean U_Update(String UID, String requirement, String requirement_leader,String Strong_point, String Grade, String Major) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String str = "SELECT * FROM link_team.student_info WHERE UID=?";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, UID);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("用户不存在");
                System.out.println("信息添加失败");

                return false ;
            } else {
                String tem = "UPDATE link_team.student_info set requirement=?,requirement_leader=?,Strong_point=?,Grade=?,Major=? where UID=?";
                System.out.println("信息添加成功");
                PreparedStatement pstmt1 = Druid_Utils.getConnection().prepareStatement(tem);
                pstmt1.setString(1, requirement);
                pstmt1.setString(2, requirement_leader);
                pstmt1.setString(3, Strong_point);
                pstmt1.setString(4, Grade);
                pstmt1.setString(5, Major);
                pstmt1.setString(6, UID);
                pstmt1.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            System.out.println("更新table失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------打印学生信息-----------------------------------------------//
    public static  boolean U_seek(String UID) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            if (UID != null) {
                String str = "SELECT * FROM link_team.student_info WHERE UID=?";
                con= Druid_Utils.getConnection();
                pstmt = con.prepareStatement(str);
                pstmt.setString(1, UID);
            } else {
                String str = "SELECT * FROM link_team.student_info ";
                con= Druid_Utils.getConnection();
                pstmt = con.prepareStatement(str);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd= rs.getMetaData();
            if (!rs.next()) {
                System.out.println("用户不存在");
                System.out.println("搜索失败");

                return false;
            } else {
                do {
                    for (int i = 1; i < rsd.getColumnCount(); i++) {
                        if (rs.getString(i) == null) {
                            System.out.print("null"+" ");
                        } else {
                            System.out.println(rs.getString(i)+" ");
                        }
                    }
                    System.out.print("\n");
                } while (rs.next());
            }
            System.out.println("搜索成功");

            return true;
        } catch (Exception e) {
            System.out.println("获取table失败");

            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------队伍信息注册-----------------------------------------------//
    public static  boolean T_signUp(String TID, String TName, String UID_Leader, String Start_time, String End_time, String Max_num, String Exist_num, String Introduct) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String str = "SELECT * FROM link_team.team_table WHERE TID=? ";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, TID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("用户已存在");
                System.out.println("注册失败");

                return false;
            } else {
                String tem = "INSERT INTO link_team.team_table" +
                        "( TID, TName, Student_ID_leader, Start_time,End_time,Max_num,Exist_num,Introduction)" +
                        " values(?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt1 = Druid_Utils.getConnection().prepareStatement(tem);
                pstmt1.setString(1, TID);
                pstmt1.setString(2, TName);
                pstmt1.setString(3, UID_Leader);
                pstmt1.setString(4, Start_time);
                pstmt1.setString(5, End_time);
                pstmt1.setString(6, Max_num);
                pstmt1.setString(7, Exist_num);
                pstmt1.setString(8, Introduct);
                pstmt1.executeUpdate();
                System.out.println("注册成功");

                return true;
            }
        } catch (Exception e) {
            System.out.println("注册失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------打印队伍信息-----------------------------------------------//
    public static  boolean T_seek(String TID) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            if (TID != null) {
                String str = "SELECT * FROM link_team.team_table WHERE TID=?";
                con= Druid_Utils.getConnection();
                pstmt = con.prepareStatement(str);
                pstmt.setString(1, TID);
            } else {
                String str = "SELECT * FROM link_team.team_table ";
                con= Druid_Utils.getConnection();
                pstmt = con.prepareStatement(str);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd= rs.getMetaData();
            if(rs.next()){
                do{
                    for (int i = 1; i < rsd.getColumnCount(); i++) {
                        if (rs.getString(i) == null) {
                            System.out.print("\tnull");
                        } else {
                            System.out.println(rs.getString(i));
                        }
                        System.out.print("\n");
                    }
                }while(rs.next());
            }else{
                System.out.println("NULL");
                return false;
            }

            System.out.println("搜索成功");

            return true;
        } catch (Exception e) {
            System.out.println("获取table失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------注册队伍表-----------------------------------------------//
    public static  boolean G_signUp(String TID, String UID) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String str = "SELECT * FROM link_team.group_table WHERE TID=? ";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, TID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("用户已存在");
                System.out.println("注册失败");

                return false;
            } else {
                String tem = "INSERT INTO link_team.group_table(TID,UID) values(?,?)";
                PreparedStatement pstmt1 = con.prepareStatement(tem);
                pstmt1.setString(1, TID);
                pstmt1.setString(2, UID);
                pstmt1.executeUpdate();
                System.out.println("注册成功");

                return true;
            }
        } catch (Exception e) {
            System.out.println("注册失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------查询队伍表-----------------------------------------------//
    public static  boolean G_seek(String TID, String UID) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            if (TID == null || UID == null) {
                System.out.println("TID or UID ERROR");
                return false;
            }
            String str = "SELECT * FROM link_team.group_table Where TID=? And UID =? ";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, TID);
            pstmt.setString(2, UID);
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd= rs.getMetaData();
            if (!rs.next()) {
                System.out.println("表为空");
                System.out.println("搜索失败");

                return false;
            } else {
                do {
                    for (int i = 1; i <= rsd.getColumnCount(); i++) {
                        if (rs.getString(i) == null) {
                            System.out.print("\tnull");
                        } else {
                            System.out.println(rs.getString(i));
                        }
                        System.out.print("\n");
                    }
                } while (rs.next());
                System.out.println("搜索成功");

                return true;
            }
        } catch (Exception e) {
            System.out.println("获取table失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    public static  boolean G_seek_T(String TID) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            if (TID == null) {
                System.out.println("TID ERROR");
                return false;
            }
            String str = "SELECT * FROM link_team.group_table Where TID=?";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, TID);
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd= rs.getMetaData();
            if (!rs.next()) {
                System.out.println("表为空");
                System.out.println("搜索失败");

                return false;
            } else {
                do {
                    for (int i = 1; i <= rsd.getColumnCount(); i++) {
                        if (rs.getString(i) == null) {
                            System.out.print("\tnull");
                        } else {
                            System.out.println(rs.getString(i));
                        }
                        System.out.print("\n");
                    }
                } while (rs.next());
            }
            System.out.println("搜索成功");

            return true;
        } catch (Exception e) {
            System.out.println("获取table失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    public static  boolean G_seek_U(String UID) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            if (UID == null) {
                System.out.println("UID ERROR");

                return false;
            }
            String str = "SELECT * FROM  link_team.group_table Where UID=?";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, UID);
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd= rs.getMetaData();
            if (!rs.next()) {
                System.out.println("表为空");
                System.out.println("搜索失败");

                return false;
            } else {
                do {
                    for (int i = 1; i <= rsd.getColumnCount(); i++) {
                        if (rs.getString(i) == null) {
                            System.out.print("\tnull");
                        } else {
                            System.out.println(rs.getString(i));
                        }
                        System.out.print("\n");
                    }
                } while (rs.next());
            }
            System.out.println("搜索成功");

            return true;
        } catch (Exception e) {
            System.out.println("获取table失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------查询队伍表-----------------------------------------------//
    public static  boolean C_signUp(String CID, String C_name, String C_level, String C_class) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String str = "SELECT * FROM link_team.contest_info WHERE CID=? ";
            con= Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, CID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("比赛已存在");
                System.out.println("注册失败");

                return false;
            } else {
                String tem = "INSERT INTO link_team.contest_info(CID,C_name,C_level,C_class) values(?,?,?,?)";
                PreparedStatement pstmt1 = Druid_Utils.getConnection().prepareStatement(tem);
                pstmt1.setString(1, CID);
                pstmt1.setString(2, C_name);
                pstmt1.setString(3, C_level);
                pstmt1.setString(4, C_class);
                pstmt1.executeUpdate();
                System.out.println("注册成功");

                return true;
            }
        } catch (Exception e) {
            System.out.println("注册失败");

            return false;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

    //--------------------------------------查询比赛-----------------------------------------------//
    public static  boolean C_seek(String CID, String C_name, String C_level, String C_class) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        StringBuffer str = new StringBuffer();
        StringBuffer str1 = new StringBuffer();
        List<String> tem =new ArrayList<String>() ;
        List<String> tem1 =new ArrayList<String>();
        try {
            if (CID == null && C_name == null && C_level == null && C_class == null) {
                str.append("SELECT * FROM contest_info");
                pstmt = Druid_Utils.getConnection().prepareStatement(str.toString());
            } else {
                str.append("SELECT * FROM contest_info Where ");
            }
            if (CID != null) {
                tem.add("CID=?");
                tem1.add(CID);
            }
            if (C_name != null) {
                tem.add("C_name=?");
                tem1.add(C_name);
            }
            if (C_level != null) {
                tem.add("C_level=?");
                tem1.add(C_level);
            }
            if (C_class != null) {
                tem.add("C_class=?");
                tem1.add(C_class);
            }
            str.append(tem.get(0));
            for (int i = 1; i < tem.size(); i++) {
                str.append(" and ");
                str.append(tem.get(i));
            }
            pstmt = Druid_Utils.getConnection().prepareStatement(str.toString());
            for (int i = 0; i <tem1.size(); i++) {
                pstmt.setString(i+1, tem1.get(i).toString());
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd= rs.getMetaData();
            if (!rs.next()) {
                System.out.println("表为空");
                System.out.println("搜索失败");

                return false;
            } else {
                do {
                    for (int i = 1; i <= rsd.getColumnCount(); i++) {
                        if (rs.getString(i) == null) {
                            System.out.print("\tnull");
                        } else {
                            System.out.println(rs.getString(i));
                        }
                        System.out.print("\n");
                    }
                } while (rs.next());
            }
            System.out.println("搜索成功");

            return true;
        } catch (Exception e) {
            System.out.println("获取table失败");

            return true;
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
    }

}
