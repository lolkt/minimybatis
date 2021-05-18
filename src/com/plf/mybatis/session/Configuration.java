/**
 *
 */
package com.plf.mybatis.session;


import com.plf.mybatis.binding.MapperRegistry;
import com.plf.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * mybatis核心配置类
 *
 * 动态代理：
 * 当我们使⽤Configuration的getMapper⽅法时，会调⽤mapperRegistry.getMapper⽅法，
 * ⽽该⽅法⼜会调⽤ mapperProxyFactory.newInstance(sqlSession)来⽣成⼀个具体的代理
 *
 * @author PLF
 * @date 2019年3月7日
 * @version 1.0
 */
public class Configuration {
    /**配置项*/
    public static Properties PROPS = new Properties();

    /** mapper代理注册器 */
    protected final MapperRegistry mapperRegistry = new MapperRegistry();

    /** mapper文件的select/update语句的id和SQL语句属性 **/
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * addMapper
     *
     * @param type
     */
    public <T> void addMapper(Class<T> type) {
        this.mapperRegistry.addMapper(type);
    }

    /**
     * getMapper
     *
     * @param type
     * @param sqlSession
     * @return
     * @see
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    /**
     * addMappedStatement
     *
     * @param key
     * @param mappedStatement
     */
    public void addMappedStatement(String key, MappedStatement mappedStatement) {
        this.mappedStatements.put(key, mappedStatement);
    }

    /**
     * 获取MappedStatement
     *
     * @param id xml文件标签的id属性
     * @return
     * @see
     */
    public MappedStatement getMappedStatement(String id) {
        return this.mappedStatements.get(id);
    }

    /**
     * 获取字符型属性(默认值为空字符串)
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    /**
     * 获取字符型属性(可指定默认值)
     *
     * @param key
     * @param defaultValue  默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue) {

        return PROPS.containsKey(key) ? PROPS.getProperty(key) : defaultValue;
    }

}
