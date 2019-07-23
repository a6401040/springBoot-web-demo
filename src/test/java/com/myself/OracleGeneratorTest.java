package com.myself;


import com.myself.generator.DatabaseGenerator;
import org.junit.Test;

/**
 * oracle生成器
 * @author zhangqiling
 * @date 2019/6/24
 * @version V1.0
 */
public class OracleGeneratorTest {

    @Test
    public void generator() {
        String dbType = "oracle";
        String dirveName = "oracle.jdbc.driver.OracleDriver";
        String username = "dcscontent";
        String password = "pssryx892j";
        String url = "jdbc:oracle:thin:@192.168.45.11:1521:psstestdb";

        DatabaseGenerator databaseGenerator = new DatabaseGenerator(dbType, dirveName, username, password, url);
        databaseGenerator.generator("T_USER_LOGIN");
    }
}
