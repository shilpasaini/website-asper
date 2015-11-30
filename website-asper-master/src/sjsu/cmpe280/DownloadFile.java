package sjsu.cmpe280;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DownloadFile extends HttpServlet  {
		private static final long serialVersionUID=1L;
		
		public DownloadFile(){
			super();
		}
		protected void doGet(HttpServletRequest request,HttpServletResponse response) {
			
		}
		public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	     System.out.println("i am here");
	
	     BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	     String userInfo = "";
	     if(br != null){
	    	 userInfo = br.readLine();
	     }
		 JSONParser parser=new JSONParser();
		 try {
			System.out.println("parsing json");
			JSONObject jsonObject=(JSONObject)parser.parse(userInfo);
			String fName=(String)jsonObject.get("FirstName");
			String lName=(String) jsonObject.get("LastName");
			String Email=(String)jsonObject.get("Email");
			boolean userInfo2=insertInfo(fName,lName,Email);
			System.out.println(userInfo2);
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		}
		
		public boolean insertInfo(String fN,String lN,String Email){
			try{
	        System.out.println("Loading driver...");
	        Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("Driver loaded!");
			Connection mConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmpe280Assigment","root","root");
			Statement stmt=mConnection.createStatement();
			System.out.println("insert into userInfo1 values("+"\""+fN+"\""+","+"\""+lN+"\""+","+"\""+Email+"\""+")");
			String qry="insert into userInfo1 values("+"\""+fN+"\""+","+"\""+lN+"\""+","+"\""+Email+"\""+")";
			stmt.executeUpdate(qry);
			System.out.println("inserted record");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return true;
		}

	}

	

