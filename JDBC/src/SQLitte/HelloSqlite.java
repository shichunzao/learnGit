package SQLitte;
import java.sql.*;
import SQLite.*;
import SQLite.Exception;
public class HelloSqlite {  //测试，查看数据库版本号
	Database db=new Database();
//    static final String strCreate="Create table persons03(ID integer primary key,LastName text,FirstName varchar, "
//    		+ "Adress varchar,City varchar)";
    
    //写入SQL语句
//    static final String strInsert01="insert into persons03 values(01,'james','John','Oxford','London')";
//    static final String strInsert02="insert into persons03 values(02,'Adams','George','Fifth Avenue','New York')";
//    static final String strInsert03="insert into persons03 values(03,'Bush','George','Changan','London')";
//    static final String strDisplay01="select * from persons03";  //显示全表
      static final String strDisplay03="select * from persons03 WHERE City='London'";
    
    //连接数据库
    public int connectDB(String dbPath){
    	try{
    		db.open(dbPath,0666);
    		return 1;
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return -1;
    	}
    }
    //执行方法
    public int excute(String sql){
    	try{
    		db.exec(sql,new TableFmt());//这是回调函数，用于返回处理结果
    		return 1;
    	}catch (Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    }
    //函数入口，执行测试
	public static void main(String[] args){
		HelloSqlite s=new HelloSqlite();
		System.out.println(s.db.version());//显示sqlite 的版本号
		s.connectDB("0428-6.db");  //连接一个数据库（没有数据库则创建）
		//执行SQL语句
//		s.excute(strCreate);  
//		s.excute(strInsert01);   
//		s.excute(strInsert02);  
//		s.excute(strInsert03);  
//		s.excute(strDisplay01);
		s.excute(strDisplay03);
	
	}
}
//处理查询结果：实现了SQLite.Callback接口，接着将这个类的一个
//对象传递给exec的Callback参数，这样exec就会对每个查询结果调用Callback，继而实现对结果处理
class TableFmt implements Callback{
	//输出每表头
	public void columns(String[] cols){
		System.out.println("column");
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+" ");
		}
	}
//	输出列数据
	public boolean newrow(String[] cols){
		System.out.println("newrow1");
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+" ");
		}
		return false;
	}
	public void types(String[] cols){
		System.out.println("newow2");
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+" ");
		}
	}
}
