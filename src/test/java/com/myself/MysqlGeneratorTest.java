package com.myself;

/**
 * @author Kylin
 * @date 2019/6/24
 * @description: 代码生成器
 * @version: V1.0
 */

import com.myself.generator.MysqlGenerator;
import org.junit.Test;

public class MysqlGeneratorTest {

    @Test
    public void generator() {
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.generator("t_user");
    }
}
