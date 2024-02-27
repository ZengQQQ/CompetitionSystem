package db;

import Logical_opt.domain.Student;
import Logical_opt.domain.Team;
import druid_JDBC_utils.Druid_Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class logical_op {

    //---------------------------------------用户登录-----------------------------------------------//
    public static String U_login(String Student_id, String password) {

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            String str = "SELECT UID FROM student_info WHERE Student_ID=? AND password=?";
            con = Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, Student_id);
            pstmt.setString(2, password);
            System.out.println(Student_id + "\n" + password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //如果账号密码输入正确,查询账户状态
                if(checkState(Student_id)) {
                    return rs.getString("UID");
                }
                else {
                    return "-1";
                }
            }
            else {
                return "0";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(rs, pstmt, con);
        }
    }

    //校验用户的账号状态
    public static boolean checkState(String Student_id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select state from students where Student_ID=?";
        try {
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Student_id);
            preparedStatement.executeQuery();
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            String state=resultSet.getString("state");
            //System.out.println("账户状态"+state);
            if (state.equals("0")){
                //如果账号状态正常,返回true
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }
    //-----------------------------------------管理员登录-------------------------------------------//
    public static String M_login(String id, String password) {

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            String str = "SELECT num FROM manager WHERE id=? AND password=?";
            con = Druid_Utils.getConnection();
            pstmt = con.prepareStatement(str);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                    return "1";
                }
            else {
                return "0";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(rs, pstmt, con);
        }
    }
    //-----------------------------------------用户注册---------------------------------------------//

    public static boolean U_signUp( String password, String Student_Name, String Student_ID, String UName, String Tel,String Major) {
        ResultSet rs= null;
        Connection con = null;
        PreparedStatement pstmt1 = null;
        String UID=null;
        try {
            String str1 = "SELECT UID FROM student_info WHERE Student_ID=? ";
            con = Druid_Utils.getConnection();
            pstmt1 = con.prepareStatement(str1);
            pstmt1.setString(1, Student_ID);
            rs= pstmt1.executeQuery();
            if (rs.next()) {
                System.out.println("用户已存在");
                System.out.println("注册失败");
                return false;
            } else {
                //根据学号生成UID
                String hashCode=(String.valueOf(Math.abs(Student_ID.hashCode()))).substring(0,5);
                UID=10000+(int)(Math.random()*90000)+hashCode;
                //补全SQL语句
                String tem = "INSERT INTO student_info(UID,password,UName,Student_ID,Student_Name,Tel,Major) values(?,?,?,?,?,?,?)";
                PreparedStatement pstmt = Druid_Utils.getConnection().prepareStatement(tem);
                pstmt.setString(1, UID);
                pstmt.setString(2, password);
                pstmt.setString(3, UName);
                pstmt.setString(4, Student_ID);
                pstmt.setString(5, Student_Name);
                pstmt.setString(6, Tel);
                pstmt.setString(7,Major);
                pstmt.executeUpdate();
                System.out.println("注册成功");
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(rs, pstmt1, con);
        }
    }

    //返回查询到的总数目————————by SPC
    public static int findTotalCount(Map<String, String[]> condition,String tableName,String id) {
        //定义查询到的行数
        int rowCount=0;

        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        //1.定义模板初始化sql,
        String sql=null;
        if(tableName.equals("student_info")) {
             sql = "select count(*) from " + tableName + " where 1=1 and Student_ID !='"+id+"'";
        }
        else if(tableName.equals("team_table")){
            sql=  "select count(*) from " + tableName + " where 1=1 and Exist_Num>0 and Student_ID_leader !='"+id+"'";
        }
        else if(tableName.equals("students")){
            sql= "select count(*) from "+tableName+" where 1=1";
        }
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //System.out.println(keySet.toString());
        //定义参数集合
        //List<Object> params = new ArrayList<Object>();

        for(String key : keySet){

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //System.out.println(value);
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                //System.out.println("向SQL传入了条件");
                sb.append(" and "+key+" like ");
                sb.append(" '%"+value+"%' ");//？条件的值
            }
        }
        sql=sb.toString();
        try {
            con=Druid_Utils.getConnection();
            System.out.println(sb);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            rowCount=rs.getInt(1);
            //System.out.println("查询到的条数为"+rowCount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }
        return rowCount;
    }

    //分页查询，返回student_info表中信息的集合————————————by SPC
    public static List<Student> findByPage_student(int start, int rows, Map<String, String[]> condition,String id) {
        System.out.println(start);
        System.out.println(rows);
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        List<Student> list=new ArrayList<>();
        String sql = "select * from student_info where 1 = 1 and Student_ID !='"+id+"'";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数集合
        //List<Object> params = new ArrayList<Object>();

        for(String key : keySet){

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ");
                sb.append(" '%"+value+"%' ");
                //params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询参数
        sb.append(" limit " +start+","+rows );
        //添加分页查询参数
        //params.add(start);
        //params.add(rows);

        sql = sb.toString();
        System.out.println("第二个查询为"+sql);

        try {
            con=Druid_Utils.getConnection();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                String SQL="select state from students where Student_ID=?";
                String Student_ID=rs.getString("Student_ID");
                Connection connection=null;
                PreparedStatement preparedStatement=null;
                ResultSet resultSet=null;
                //查询每个用户的账户状态
                String state;
                try {
                     connection=Druid_Utils.getConnection();
                     preparedStatement=connection.prepareStatement(SQL);
                     preparedStatement.setString(1,Student_ID);
                     resultSet=preparedStatement.executeQuery();
                     resultSet.next();
                     state=resultSet.getString("state");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    Druid_Utils.close(resultSet,preparedStatement,connection);
                }
               // String UName, String student_ID, String student_Name, String tel, String strong_point,String grade, String major, String registration_time
                Student student=new Student(
                        rs.getString("UName"),
                        rs.getString("Student_ID"),
                        rs.getString("Student_Name"),
                        rs.getString("Tel"),
                        rs.getString("Strong_point"),
                        rs.getString("Grade"),
                        rs.getString("Major"),
                        rs.getString("Registration_time"),
                        state.equals("0") ?"正常":"封禁中");
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }

        return list;
    }

    //分页查询，返回team_table表中信息的集合————————————by SPC
    public static List<Team> findByPage_team(int start, int rows, Map<String, String[]> condition, String id) {
        System.out.println(start);
        System.out.println(rows);
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        List<Team> list=new ArrayList<>();
        String sql = "select * from team_table where 1 = 1 and Exist_Num>0 and Student_ID_leader !='"+id+"'";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数集合
        //List<Object> params = new ArrayList<Object>();

        for(String key : keySet){

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ");
                sb.append(" '%"+value+"%' ");
                //params.add("%"+value+"%");//？条件的值
            }
        }


        //添加分页查询参数
        sb.append(" limit " +start+","+rows );
        //添加分页查询参数
        //params.add(start);
        //params.add(rows);

        sql = sb.toString();
        System.out.println("第二个查询为"+sql);
        try {
            con=Druid_Utils.getConnection();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                Team team=new Team(rs.getString("TName"),rs.getString("Introduction"),
                        rs.getString("Start_time"),Integer.parseInt(rs.getString("Exist_Num")),rs.getString("Student_ID_leader"));
                list.add(team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }

        return list;
    }

    //分页查询，返回students表中信息的集合————————————by SPC
    public static List<Student> findByPage_manager(int start, int rows, Map<String, String[]> condition) {
        System.out.println(start);
        System.out.println(rows);
        ResultSet rs =null;
        Connection con=null;
        PreparedStatement pstmt=null;
        List<Student> list=new ArrayList<>();
        String sql = "select * from students where 1 = 1";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数集合
        //List<Object> params = new ArrayList<Object>();

        for(String key : keySet){

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ");
                sb.append(" '%"+value+"%' ");
                //params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询参数
        sb.append(" limit " +start+","+rows );
        //添加分页查询参数
        //params.add(start);
        //params.add(rows);

        sql = sb.toString();
        //System.out.println("第二个查询为"+sql);
        try {
            con=Druid_Utils.getConnection();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                String state;
                if(Integer.parseInt(rs.getString("state"))==0){
                    state="正常";
                }
                else{
                    state="封禁中";
                }
                //String student_ID, String student_Name, String major, String state
                Student student=new Student(rs.getString("Student_ID"),
                                            rs.getString("Student_Name"),
                                            rs.getString("Major"),
                                            state
                                            );
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(rs,pstmt,con);
        }

        return list;
    }

    //查询并返回邀请队伍集合——————————by SPC
    public static List<Team> returnInvitation(String id){
        Connection connection =null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //定义id列表，先查出所有邀请者的id并组成列表
        List<String> leader_id_list=new ArrayList<String>();
        //定义队伍列表，将id对应的队伍封装起来
        List<Team> list=new ArrayList<Team>();

        try {
            String sql="select TID from group_table where UID=? and state=0";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                leader_id_list.add(resultSet.getString("TID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        for (String leader_id:leader_id_list) {
            try {
                String sql = "select * from team_table where Student_ID_leader=?";
                connection = Druid_Utils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,leader_id );
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    //String TName, String introduction, String start_time, int exist_num, String UID_leader
                    list.add(new Team(
                            resultSet.getString("TName"),
                            resultSet.getString("Introduction"),
                            resultSet.getString("Start_time"),
                            Integer.parseInt(resultSet.getString("Exist_Num")),
                            resultSet.getString("Student_ID_leader")
                    ));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                Druid_Utils.close(resultSet, preparedStatement, connection);
            }
        }
     return list;
    }
}
