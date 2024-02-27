package Logical_opt.dao;

import Logical_opt.Utils.Druid_Utils;
import Logical_opt.domain.Contest;
import Logical_opt.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Contest_dao extends Basic_dao<Contest>{
    //--------------------------------------查询队伍表-----------------------------------------------//
    public int signUp(String CID, String C_name, String C_level, String C_class) {
        try {
            String str = "INSERT INTO contest_info(CID,C_name,C_level,C_class) values(?,?,?,?) ";
            List<Contest> ct = seek(CID,null,null,null);
            if (ct!=null) {
                System.out.println("已存在");
                return 0;
            } else {
                return super.update(str,CID,C_name,C_level,C_class);
            }
        } catch (Exception e) {
            System.out.println("注册失败");
            throw new RuntimeException(e);
        }


    }

    //--------------------------------------查询比赛-----------------------------------------------//
    public List<Contest> seek(String CID, String C_name, String C_level, String C_class) {
        try {
            String str ;
            str="SELECT * FROM contest_info Where (CID=? or ?='null') and （C_name=? or ?='null'） and （C_level=? or ?='null'） and （C_class = ? or ?='null'）";
            List<Contest> con= super.queryMulti(str.toString(),Contest.class,CID ,CID ,C_name,C_name, C_level,C_level, C_class, C_class );
            if (con==null) {
                System.out.println("用户不存在");
                return null;
            } else {
                System.out.println(con);
                System.out.println("搜索成功");
            }
            return con;
        } catch (Exception e) {
            System.out.println("获取table失败");
            throw new RuntimeException(e);
        }
    }

    public int drop(String CID) {
        try {
            String str = "DELETE FROM contest_info Where CID=? ";
            List<Contest> ct = seek(CID,"null","null","null");
            if (ct==null) {
                System.out.println("不存在");
                return 0;
            } else {
                return super.update( str,CID);
            }
        } catch (Exception e) {
            System.out.println("删除失败");
            throw new RuntimeException(e);
        }


    }

}
