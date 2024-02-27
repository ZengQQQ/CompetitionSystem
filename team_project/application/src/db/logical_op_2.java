package db;

import druid_JDBC_utils.Druid_Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public  class logical_op_2 {

    private static int fetchDirection;



    //--------------------------------------信息补全------------------------------------------------//
    public static  boolean U_Finish(String UID,String requirement,String requirement_leader,String Strong_point, String Grade, String Major,String UName,String Tel) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
                String tem = "UPDATE link_team.student_info set requirement=?,requirement_leader=?,Strong_point=?,Grade=?,Major=?,UName=?,Tel=? where UID=?";
                System.out.println("信息添加成功");
                PreparedStatement pstmt1 = Druid_Utils.getConnection().prepareStatement(tem);
                pstmt1.setString(1, requirement);
                pstmt1.setString(2, requirement_leader);
                pstmt1.setString(3, Strong_point);
                pstmt1.setString(4, Grade);
                pstmt1.setString(5, Major);
                pstmt1.setString(6, UName);
                pstmt1.setString(7, Tel);
                pstmt1.setString(8, UID);
                pstmt1.executeUpdate();
                return true;
        }
        catch (Exception e) {
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
                con=Druid_Utils.getConnection();
                pstmt = con.prepareStatement(str);
                pstmt.setString(1, UID);
            } else {
                String str = "SELECT * FROM link_team.student_info ";
                con=Druid_Utils.getConnection();
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

    //--------------------------------------创建队伍-----------------------------------------------//
    public static  boolean createTeam(String TName, String Max_num,String Introduction,String Student_ID_leader) {
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            con=Druid_Utils.getConnection();
            String sql = "INSERT INTO link_team.team_table" +
                    "( TName, Introduction,Max_num,Exist_num,Student_ID_leader)" +
                    " values(?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, TName);
                pstmt.setString(2, Introduction);
                pstmt.setString(3, Max_num);
                pstmt.setString(4, Max_num);
                pstmt.setString(5, Student_ID_leader);
                pstmt.executeUpdate();
                System.out.println("注册成功");
                return true;
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
          finally {
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
                con=Druid_Utils.getConnection();
                pstmt = con.prepareStatement(str);
                pstmt.setString(1, TID);
            } else {
                String str = "SELECT * FROM link_team.team_table ";
                con=Druid_Utils.getConnection();
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
//    public static  boolean G_signUp(String TName, String Student_ID_leader,  String Max_num, String Exist_num, String Introduction) {
//        ResultSet rs =null;
//        Connection con=null;
//        PreparedStatement pstmt=null;
//        try {
//            String str =  "INSERT INTO team_table (TName, Introduction,Max_num, Exist_num,Student_ID_leader) VALUES (?,?,?,?,?) ";
//            con=Druid_Utils.getConnection();
//            pstmt = con.prepareStatement(str);
//            pstmt.setString(1, Student_ID_leader);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                System.out.println("用户已存在");
//                System.out.println("注册失败");
//                return false;
//            } else {
//                System.out.println("注册成功");
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println("注册失败");
//
//            return false;
//        }finally {
//            Druid_Utils.close(rs,pstmt,con);
//        }
//    }

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
            con=Druid_Utils.getConnection();
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
            con=Druid_Utils.getConnection();
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
            String str = "SELECT * FROM link_team.group_table Where UID=?";
            con=Druid_Utils.getConnection();
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
            con=Druid_Utils.getConnection();
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
