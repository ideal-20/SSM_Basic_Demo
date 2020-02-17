package cn.ideal.test;

import cn.ideal.dao.AccountDao;
import cn.ideal.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class TestMabatis {

    private InputStream inputStream;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    /*
        单独测试Mybatis时所用，经过整合后已经删除了 SqlMapConfig.xml文件
        配置到了 applicationContext.xml 中去
     */

    @Before
    public void init() throws Exception{
        //加载配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建 SqlSessionFactory 对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建 SqlSession 对象
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        //提交事务
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }


    @Test
    public void TestFindAll(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts){
            System.out.println("-----------------------------------------------------");
            System.out.println(account);
        }
    }

    @Test
    public void TestAddAccount(){
        Account account = new Account();
        account.setName("王五");
        account.setMoney(1000d);

        accountDao.addAccount(account);


    }
}
