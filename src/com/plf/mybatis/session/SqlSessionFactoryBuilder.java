/**
 *
 */
package com.plf.mybatis.session;


import com.plf.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;


/**
 * SqlSessionFactoryBuilder.java
 *
 * @author PLF
 * @date 2019年3月7日
 */
public class SqlSessionFactoryBuilder {

    /**
     * build
     *
     * @param fileName
     * @return
     * @see
     */
    public SqlSessionFactory build(String fileName) {
        InputStream inputStream = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);
        return build(inputStream);
    }

    /**
     * build
     *
     * @param inputStream
     * @return
     * @see
     */
    public SqlSessionFactory build(InputStream inputStream) {
        try {
            Configuration.PROPS.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultSqlSessionFactory(new Configuration());
    }
}
