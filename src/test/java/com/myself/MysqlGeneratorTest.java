package com.myself;

import com.myself.generator.DatabaseGenerator;
import org.junit.Test;

/**
 * mysql生成器
 * @author zhangqiling
 * @date 2019/6/24
 * @version V1.0
 */
public class MysqlGeneratorTest {

    @Test
    public void generator() {
        String dbType = "mysql";
        String dirveName = "com.mysql.cj.jdbc.Driver";
        String username = "zql";
        String password = "abcd1234";
        String url = "jdbc:mysql://192.168.184.129/myself?useSSL=false&characterEncoding=utf8";

        DatabaseGenerator databaseGenerator = new DatabaseGenerator(dbType, dirveName, username, password, url);
        databaseGenerator.generator("t_user");
    }
}
