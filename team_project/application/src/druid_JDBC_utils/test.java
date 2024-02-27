package druid_JDBC_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public PreparedStatement preparedStatement=null;
    public Connection connection=null;
    public ResultSet resultSet=null;
    public void find(String Student_ID,String Student_Name,String Grade,String Major) throws SQLException {
        if(Student_ID==null){
            Student_ID="_%";
        }
        if(Student_Name==null){
            Student_Name="_%";
        }
        if(Grade==null){
            Grade="_%";
        }
        if(Major==null){
            Major="_%";
        }
        System.out.println(Grade);
        try {
            this.connection=Druid_Utils.getConnection();
            String str="select * from  link_team.student_info where Student_ID like ? and Student_Name like ? and Grade like ? and Major like ? ";
            this.preparedStatement=connection.prepareStatement(str);
            preparedStatement.setString(1,Student_ID);
            preparedStatement.setString(2,Student_Name);
            preparedStatement.setString(3,Grade);
            preparedStatement.setString(4,Major);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("查询成功");
                System.out.println(resultSet.getString("UID")+"\n"+resultSet.getString("UName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

class T{
    public static void main(String[] args) throws SQLException {
        test t=new test();
        t.find(null,null,null,"数理统计");
    }
}
