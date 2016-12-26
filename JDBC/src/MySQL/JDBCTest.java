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
	 * DriverMAnager �������Ĺ�����
	 */
	public void testDriverManager(){ String driverClass=null;
	//1.׼���������ݿ��4���ַ�����
	//������ȫ����
	 String driverClass="com.mysql.jdbc.Driver";
	//JDBC URL
	 String jdbcUrl="jdbc:mysql://localhost:3306/test";
	 //user
     String user="root";
     // password
	 String password="password"; 
	 
	 //2.�������ݿ���������(��Ӧ��Driver ʵ��������ע�������ľ�̬�����ע������) ������ע���������ɳ���
	// DriverManager.registerDriver(Class.forName(driverClass).newInstance());
	 Class.forName(driverClass);
	 
	 //3.ͨ��DriverManager��getConnection()������ȡ���ݿ�����
	 Connection connection=
				DriverManager.getConnection(jdbcUrl, user, password);
     System.out.println(connection);
		
	}
	/**
	 * Driver��һ���ӿڣ����ݿ⳧�̱���ʵ�ֵĽӿڣ��ܴ���ӿ��������ݿ�
	 * ����ͨ��
	 * һ������mysql����
	 * 1.��ѹmysql-connector-java-5.1.38.zip
	 * 2.�ڵ�ǰ��Ŀ���½�libĿ¼
	 * 3.��mysql-connector-java-5.1.26-bin.jar���Ƶ�libĿ¼��
	 * 4.�Ҽ�build path��add to buildpath���뵽��·���¡�
	 */
     public void testDriver() throws SQLException{
    	 //1.����Driverʵ����Ķ���
    	 Driver driver=new com.mysql.jdbc.Driver();
    	//2.׼���������ݿ�Ļ�����Ϣ��url��user��password
    	 // driver.connect(url,info);
    	 //URL·����    jdbc:<��Э��>:<������>
    	 String url="jdbc:mysql://localhost:3306/test";
    	 Properties info=new Properties();
    	 info.put("user", "root");
    	 info.put("password", "1111");
    	 
    	 //3.����Driver�ӿڵ�connect��url��info����ȡ���ݿ�����
    	 Connection connection=(Connection) driver.connect(url,info);
    	 System.out.println(connection);
     }
/**
 * Ŀ��:��дһ��ͨ�õķ������ڲ��޸�Դ���������£����Ի�ȡ�κ����ݿ����ӡ�
*��������������ݿ�����Driverʵ�����ȫ������url��user��password����һ�������ļ��У�ͨ���޸������ļ��ķ�ʽʵ�ֺ;�������ݿ���
 *��������ص㣺1.������ԭʼ��Driver  2.�ķ������κ����ݽ���
 *���⣺���������õ���Driver  ���忪���в���Driver
 */
     public Connection getConnection()throws Exception{
    	 String driverClass=null;
    	 String jdbcUrl=null;
    	 String user=null;
    	 String password=null; 
    	 //��ȡ��·���µ�jdbc.properties�ļ�
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
    	 //ͨ�����䳣��Driver����
    	 info.put("user", user);
    	 info.put("password", password);
    	 //ͨ��Driver��connect�������ݿ�����
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
     



