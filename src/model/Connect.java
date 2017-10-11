package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect
{
  /* Error */
  public static void main(String[] args)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 16	model/Connect:getConnection	()Ljava/sql/Connection;
    //   5: astore_1
    //   6: getstatic 20	java/lang/System:out	Ljava/io/PrintStream;
    //   9: ldc 26
    //   11: invokevirtual 28	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   14: goto +36 -> 50
    //   17: astore_2
    //   18: aload_2
    //   19: invokevirtual 34	java/lang/Exception:printStackTrace	()V
    //   22: aload_1
    //   23: invokeinterface 39 1 0
    //   28: goto +33 -> 61
    //   31: astore 4
    //   33: goto +28 -> 61
    //   36: astore_3
    //   37: aload_1
    //   38: invokeinterface 39 1 0
    //   43: goto +5 -> 48
    //   46: astore 4
    //   48: aload_3
    //   49: athrow
    //   50: aload_1
    //   51: invokeinterface 39 1 0
    //   56: goto +5 -> 61
    //   59: astore 4
    //   61: return
    // Line number table:
    //   Java source line #11	-> byte code offset #0
    //   Java source line #15	-> byte code offset #2
    //   Java source line #17	-> byte code offset #6
    //   Java source line #18	-> byte code offset #14
    //   Java source line #19	-> byte code offset #17
    //   Java source line #21	-> byte code offset #18
    //   Java source line #27	-> byte code offset #22
    //   Java source line #28	-> byte code offset #28
    //   Java source line #29	-> byte code offset #31
    //   Java source line #24	-> byte code offset #36
    //   Java source line #27	-> byte code offset #37
    //   Java source line #28	-> byte code offset #43
    //   Java source line #29	-> byte code offset #46
    //   Java source line #33	-> byte code offset #48
    //   Java source line #27	-> byte code offset #50
    //   Java source line #28	-> byte code offset #56
    //   Java source line #29	-> byte code offset #59
    //   Java source line #34	-> byte code offset #61
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	args	String[]
    //   1	50	1	conn	Connection
    //   17	2	2	e	Exception
    //   36	13	3	localObject	Object
    //   31	1	4	localSQLException	java.sql.SQLException
    //   46	1	4	localSQLException1	java.sql.SQLException
    //   59	1	4	localSQLException2	java.sql.SQLException
    // Exception table:
    //   from	to	target	type
    //   2	14	17	java/lang/Exception
    //   22	28	31	java/sql/SQLException
    //   2	22	36	finally
    //   37	43	46	java/sql/SQLException
    //   50	56	59	java/sql/SQLException
  }
  
  public static Connection getConnection()
    throws Exception
  {
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://localhost:3306/library2?autoReconnect=true&useSSL=false";
    String user = "root";
    String passwd = "root";
    
    return DriverManager.getConnection(url, user, passwd);
  }
}
