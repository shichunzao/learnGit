package SQLitte;
import java.sql.*;
import SQLite.*;
import SQLite.Exception;
public class HelloSqlite {  //���ԣ��鿴���ݿ�汾��
	Database db=new Database();
//    static final String strCreate="Create table persons03(ID integer primary key,LastName text,FirstName varchar, "
//    		+ "Adress varchar,City varchar)";
    
    //д��SQL���
//    static final String strInsert01="insert into persons03 values(01,'james','John','Oxford','London')";
//    static final String strInsert02="insert into persons03 values(02,'Adams','George','Fifth Avenue','New York')";
//    static final String strInsert03="insert into persons03 values(03,'Bush','George','Changan','London')";
//    static final String strDisplay01="select * from persons03";  //��ʾȫ��
      static final String strDisplay03="select * from persons03 WHERE City='London'";
    
    //�������ݿ�
    public int connectDB(String dbPath){
    	try{
    		db.open(dbPath,0666);
    		return 1;
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return -1;
    	}
    }
    //ִ�з���
    public int excute(String sql){
    	try{
    		db.exec(sql,new TableFmt());//���ǻص����������ڷ��ش�����
    		return 1;
    	}catch (Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    }
    //������ڣ�ִ�в���
	public static void main(String[] args){
		HelloSqlite s=new HelloSqlite();
		System.out.println(s.db.version());//��ʾsqlite �İ汾��
		s.connectDB("0428-6.db");  //����һ�����ݿ⣨û�����ݿ��򴴽���
		//ִ��SQL���
//		s.excute(strCreate);  
//		s.excute(strInsert01);   
//		s.excute(strInsert02);  
//		s.excute(strInsert03);  
//		s.excute(strDisplay01);
		s.excute(strDisplay03);
	
	}
}
//�����ѯ�����ʵ����SQLite.Callback�ӿڣ����Ž�������һ��
//���󴫵ݸ�exec��Callback����������exec�ͻ��ÿ����ѯ�������Callback���̶�ʵ�ֶԽ������
class TableFmt implements Callback{
	//���ÿ��ͷ
	public void columns(String[] cols){
		System.out.println("column");
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+" ");
		}
	}
//	���������
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
