package TNT_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Reporter;

public class ConnectSQLServer {

		static String value;
		
		public static String SQLQuery(String PCBString) throws ClassNotFoundException, SQLException {
			
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		//Local Database setting final String url = "jdbc:sqlserver://DESKTOP-C6M5A7U\\SQLEXPRESS:1433;database=stb_production;authenticationScheme=NTLM;integratedSecurity=false;trustServerCertificate=true";
		final String url = "jdbc:sqlserver://172.20.10.149\\PRODUCTION:1433;database=stb_production;authenticationScheme=NTLM;integratedSecurity=false;trustServerCertificate=true";
		// Local User final String user ="Lalit.Soni" ;
		// Local Password final String password ="Password123" ;
		final String user ="sa" ;
		final String password ="Sql2012PM" ;
		
		
		
		
		Connection connection = DriverManager.getConnection(url,user,password);
		
		Statement st = connection.createStatement();
		String Sql = "select id_status from production_event where pcb_num = '+PCBString+'"; 
		ResultSet rs = st.executeQuery(Sql);
		
		if(rs.next()) {
			value = rs.getString(1);
			System.out.println(value);
			Reporter.log(value);
		}
		return value;
		
		
		//System.out.println(viewValue(connection,Sql));
		
		 //ResultSetMetaData rsMetaData = rs.getMetaData();
		
		/*for (int i = 1; i <= 2; i++) {
            System.out.print(rsMetaData.getColumnName(i)
                             + "\t");
        }
		while (rs.next()) {
			
			System.out.println("\n");
			
			value = rs.getString(1);
			
			//System.out.print(rs.getString(1) +  "\t\t"                    + rs.getString(2));
		
		}
		*/
	
		}
		

		}