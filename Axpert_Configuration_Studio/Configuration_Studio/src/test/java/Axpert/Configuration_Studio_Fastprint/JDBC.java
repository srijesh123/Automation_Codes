package Axpert.Configuration_Studio_Fastprint;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class JDBC {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection("jdbc:postgresql://172.16.0.135:5432/qatestbed","testbed","log");
		
		Statement stmt = con.createStatement();
		
		String query = "select caption from tstructs at2 order by 1";
		ResultSet res= stmt.executeQuery(query);
		while (res.next())
		{
		String groupfolder = res.getString("caption");	
//		String caption = res.getString("caption");	
//		System.out.print(" " + itemcat);
//		String billto = res.getString("billto");	
//		System.out.print(" " + itemname);
//		String shipto = res.getString("ship_to");	
//		System.out.print(" " + quantity);
//		System.out.println(itemcat + " " + itemname + " " + quantity);
//		String Item_details = address + " " + billto + " " + shipto;
//		System.out.println(groupfolder + " " + caption);
//		System.out.println(groupfolder);
		
		
		String[] Expected_Value_DB = {groupfolder};
//		System.out.println(Expected_Value_DB);
		for (int i=0; i<Expected_Value_DB.length; i++)
		{
			System.out.println(Expected_Value_DB[i]);
		}
		String str = Arrays.toString(Expected_Value_DB);
		String Expected = str;
		assertEquals(str, Expected);
		}
	}
}


