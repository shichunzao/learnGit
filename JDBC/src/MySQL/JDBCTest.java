package MySQL;
import java.io.InputStream;
import java.sql.*;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;

//import org.junit.Test;
public class JDBCTest {
	
	/**
	 * DriverMAnager 是驱动的管理类
	 */
	public void testDriverManager(){ String driverClass=null;
	//1.准备连接数据库的4个字符串。
	//驱动的全类名
	 String driverClass="com.mysql.jdbc.Driver";
	//JDBC URL
	 String jdbcUrl="jdbc:mysql://localhost:3306/test";
	 //user
     String user="root";
     // password
	 String password="password"; 
	 
	 //2.加载数据库驱动程序(对应的Driver 实现类中有注册驱动的静态代码块注册驱动) 他可以注册多个驱动成程序
	// DriverManager.registerDriver(Class.forName(driverClass).newInstance());
	 Class.forName(driverClass);
	 
	 //3.通过DriverManager的getConnection()方法获取数据库连接
	 Connection connection=
				DriverManager.getConnection(jdbcUrl, user, password);
     System.out.println(connection);
		
	}
	/**
	 * Driver是一个接口：数据库厂商必须实现的接口，能从其接口连接数据库
	 * 可以通过
	 * 一、加入mysql驱动
	 * 1.解压mysql-connector-java-5.1.38.zip
	 * 2.在当前项目下新建lib目录
	 * 3.将mysql-connector-java-5.1.26-bin.jar复制到lib目录下
	 * 4.右键build path，add to buildpath加入到类路径下。
	 */
     public void testDriver() throws SQLException{
    	 //1.创建Driver实现类的对象
    	 Driver driver=new com.mysql.jdbc.Driver();
    	//2.准备连接数据库的基本信息：url，user，password
    	 // driver.connect(url,info);
    	 //URL路径：    jdbc:<子协议>:<子名称>
    	 String url="jdbc:mysql://localhost:3306/test";
    	 Properties info=new Properties();
    	 info.put("user", "root");
    	 info.put("password", "1111");
    	 
    	 //3.调用Driver接口的connect（url，info）获取数据库连接
    	 Connection connection=(Connection) driver.connect(url,info);
    	 System.out.println(connection);
     }
/**
 * 目的:编写一个通用的方法，在不修改源程序的情况下，可以获取任何数据库连接。
*解决方案：把数据库驱动Driver实现类的全类名、url、user、password放入一个配置文件中，通过修改配置文件的方式实现和具体的数据库解耦。
 *这个方法特点：1.用来最原始的Driver  2.改方法和任何数据解耦
 *问题：本方法中用到了Driver  具体开发中不用Driver
 */
     public Connection getConnection()throws Exception{
    	 String driverClass=null;
    	 String jdbcUrl=null;
    	 String user=null;
    	 String password=null; 
    	 //读取类路径下的jdbc.properties文件
    	 InputStream in=
    			 getClass().getClassLoader().getResourceAsStream("jdbc.properties");
    	 Properties properties=new Properties();         
    	 Driver driver =(Driver)Class.forName(driverClass).newInstance();
    	 Properties info=new Properties();
    	 properties.load(in);
    	 driverClass=properties.getProperty("driver");
    	 jdbcUrl=properties.getProperty("jdbcUrl");
    	 user=properties.getProperty("user");
    	 password=properties.getProperty("password");
    	 //通过反射常见Driver对象
    	 info.put("user", user);
    	 info.put("password", password);
    	 //通过Driver的connect方法数据库连接
    	 Connection connection=  (Connection) driver.connect(jdbcUrl,info);
    	 return connection;
     }
     public void testGetConnection()throws Exception{
    	 System.out.println(getConnection());
     }
//     public static void main(String[] args) throws Exception{
//    	 TestJDBC t=new TestJDBC();
//    	 t.testGetConnection();
//     }
} 
     



