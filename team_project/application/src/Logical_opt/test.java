package Logical_opt;

import Logical_opt.Utils.Druid_Utils;
import Logical_opt.dao.Student_dao;

public class test {
    public static void main(String[] args) {
        try {
            Student_dao a =new Student_dao();
            a.seek("null");
        }catch (Exception e){
            System.out.println("false");
        }
    }
}
