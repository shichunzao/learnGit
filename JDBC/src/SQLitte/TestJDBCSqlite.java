package SQLitte;

import java.sql.*;

public class TestJDBCSqlite {

/**
* @param args
*/
public static void main(String[] args) {
// TODO Auto-generated method stub
try {
long start = System.currentTimeMillis();
// ����SQLite��JDBC
Class.forName("org.sqlite.JDBC");

// ����һ�����ݿ���zieckey.db�����ӣ���������ھ��ڵ�ǰĿ¼�´���֮
//��ɫ����·��Ҫ��ȫСд����д�ᱨ��
Connection conn = DriverManager.getConnection("jdbc:sqlite://d:/TestJDBCSqlite.db");
long end = System.currentTimeMillis();
System.out.println("�������ݿ��ļ������Ӻķ�ʱ�䣺" + (end - start));
conn.close();
start = System.currentTimeMillis();
conn = DriverManager.getConnection("jdbc:sqlite://d:/test.db");
end = System.currentTimeMillis();
System.out.println("���ݿ����Ӻķ�ʱ�䣺" + (end - start));
start = System.currentTimeMillis();
Statement stat = conn.createStatement();
// ����һ��������
stat.executeUpdate("create table tbl1(name varchar(20), salary int);");
end = System.currentTimeMillis();
System.out.println("������ķ�ʱ�䣺" + (end - start));
// ��������
start = System.currentTimeMillis();
stat.executeUpdate("insert into tbl1 values('ZhangSan',8000);");
stat.executeUpdate("insert into tbl1 values('LiSi',7800);");
stat.executeUpdate("insert into tbl1 values('WangWu',5800);");
stat.executeUpdate("insert into tbl1 values('ZhaoLiu',9100);");
end = System.currentTimeMillis();
System.out.println("�����������ݺķ�ʱ�䣺" + (end - start));
start = System.currentTimeMillis();
ResultSet rs = stat.executeQuery("select * from tbl1;"); // ��ѯ����
while (rs.next()) { // ����ѯ�������ݴ�ӡ����
System.out.print("name = " + rs.getString("name") + " "); // ������һ
System.out.println("salary = " + rs.getString("salary")); // �����Զ�
}
rs.close();
end = System.currentTimeMillis();
System.out.println("��ѯ���ݺķ�ʱ�䣺" + (end - start));
conn.close(); // �������ݿ������

} catch (Exception e) {
e.printStackTrace();
}
}
}