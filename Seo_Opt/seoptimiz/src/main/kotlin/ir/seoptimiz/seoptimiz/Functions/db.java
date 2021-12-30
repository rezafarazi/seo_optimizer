package ir.seoptimiz.seoptimiz.Functions;

import java.sql.*;
import java.util.ArrayList;
import ir.seoptimiz.Models.Questions_Model;

public class db 
{



    //Global Variables Start
    private static String Connection_URL="jdbc:sqlite:DB.db";
    public static long static_user_id=0;
    public static String static_username="";
    public static String static_password="";
    public static ArrayList<ArrayList<String>> Users=new ArrayList<>();
    public static ArrayList<ArrayList<String>> Data_Tb=new ArrayList<>();
    public static ArrayList<ArrayList<String>> Question_Categories=new ArrayList<>();
    //Global Variables End







    //Get Check Login User Start
    public static boolean CHECK_USER(String username,String password)
    {
        Users.clear();
        int i=GET_SELECT("SELECT * FROM users WHERE username LIKE '"+username+"' AND password LIKE '"+password+"'",Users);                
        if(i<=0)
        {
            return false;
        }
        else
        {
            ArrayList<String> user=Users.get(0);
            static_user_id=Long.parseLong(user.get(0));
            static_username=user.get(3);
            static_password=user.get(4);
            return true;
        }
    }
    //Get Check Login User End






    //INSERT NEW Data Start
    public static boolean INSERT_NEW_Data(String name,String Json)
    {
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            st.execute("INSERT INTO data_tb(user_id,data,name) VALUES("+static_user_id+",'"+Json+"','"+name+"')");
            st.close();
            con.close();
            return true;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return false;
    }
    //INSERT NEW Data End





    //Get Select Data_Tb Start
    public static void GET_SELECT_DATA_TB()
    {
        GET_SELECT("SELECT * FROM data_tb WHERE user_id="+static_user_id+";", Data_Tb);
    }
    //Get Select Data_Tb End






    //Get Delete Data From Data_Table Start
    public static boolean DELETE_Data(Long id)
    {
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            st.execute("DELETE FROM data_tb WHERE user_id="+static_user_id+" AND id="+id+";");
            st.close();
            con.close();
            return true;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return false;
    }
    //Get Delete Data From Data_Table End




    //Get Updata Data Start
    public static boolean UPDATE_Data(Long id,String title,String json)
    {
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            st.execute("UPDATE data_tb SET data='"+json+"',name='"+title+"' WHERE id="+id+" AND user_id="+static_user_id+";");
            st.close();
            con.close();
            return true;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return false;
    }
    //Get Updata Data End





    //Get Delete Data From Data_Table Start
    public static boolean Insert_New_User(String name,String family,String username,String password)
    {
        int i=GET_SELECT("SELECT * FROM users WHERE username LIKE '"+username+"' AND password LIKE '"+password+"';", new ArrayList());
        if(i<=0)
        {
            try
            {
                Connection con=DriverManager.getConnection(Connection_URL);
                Statement st=con.createStatement();
                st.execute("INSERT INTO users(name,family,username,password) VALUES('"+name+"','"+family+"','"+username+"','"+password+"');");
                st.close();
                con.close();
                return true;
            }
            catch(Exception Error)
            {
                System.out.print(Error.getMessage());
            }
        }
        return false;
    }
    //Get Delete Data From Data_Table End




    //Update User Password Start
    public static boolean Update_User_Password(String password)
    {
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            st.execute("UPDATE users SET password='"+password+"' WHERE id="+static_user_id+";");
            st.close();
            con.close();
            return true;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return false;
    }
    //Update User Password End




    //Get Select Function Start
    private static int GET_SELECT(String SQL,ArrayList<ArrayList<String>> ary)
    {
        ary.clear();
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            ResultSetMetaData rsmd=rs.getMetaData();
            int colnum_count=rsmd.getColumnCount();
            int count=0;
            while(rs.next())
            {
                count++;
                ArrayList<String> str_ary=new ArrayList<>();
                for(int i=1;i<=colnum_count;i++)
                {
                    str_ary.add(rs.getString(rsmd.getColumnName(i)));
                }
                ary.add(str_ary);
            }
            con.close();
            return count;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return 0;
    }
    //Get Select Function End





    //Get Categories For Question Start
    public static void GET_QUESTIONS_CATEGORY()
    {
        GET_SELECT("SELECT * FROM questions;", Question_Categories);
    }
    //Get Categories For Question End



    


    //INSERT NEW Question Start
    public static boolean INSERT_NEW_QUESTION(String Category,String Json)
    {
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            st.execute("INSERT INTO questions(category,questions_json) VALUES('"+Category+"','"+Json+"');");
            st.close();
            con.close();
            return true;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return false;
    } 
    //INSERT NEW Question End



    //Update Question Start
    public static boolean UPDATE_QUESTION(Questions_Model question)
    {
        try
        {
            Connection con=DriverManager.getConnection(Connection_URL);
            Statement st=con.createStatement();
            st.execute("UPDATE questions SET category='"+question.getCategory()+"',questions_json='"+question.getQuestions()+"' WHERE id="+question.getId()+";");
            st.close();
            con.close();
            return true;
        }
        catch(Exception Error)
        {
            System.out.print(Error.getMessage());
        }
        return false;
    }
    //Update Question End



}
