package Logical_opt.dao;

import Logical_opt.Utils.Druid_Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Basic_dao<T> {
    private QueryRunner qr =new QueryRunner();


    public  int update(String sql,Object... parameters){
        Connection connection=null;

        try {
            connection= Druid_Utils.getConnection();
            int update =qr.update(connection,sql,parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(null,null,connection);
        }
    }


    public List<T> queryMulti(String sql,Class<T> clazz, Object... parameters){
        Connection connection=null;

        try {
            connection= Druid_Utils.getConnection();
            return qr.query(connection,sql,new BeanListHandler<>(clazz),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(null,null,connection);
        }
    }

    public T querySingle(String sql,Class<T> clazz, Object... parameters){
        Connection connection=null;

        try {
            connection= Druid_Utils.getConnection();
            return qr.query(connection,sql,new BeanHandler<>(clazz),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(null,null,connection);
        }
    }
    public Object queryScalar(String sql, Object... parameters){
        Connection connection=null;

        try {
            connection= Druid_Utils.getConnection();
            return qr.query(connection,sql,new ScalarHandler(),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(null,null,connection);
        }
    }



}
