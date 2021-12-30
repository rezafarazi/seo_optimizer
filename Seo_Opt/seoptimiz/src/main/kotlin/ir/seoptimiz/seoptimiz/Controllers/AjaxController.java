package ir.seoptimiz.seoptimiz.Controllers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import ir.seoptimiz.Models.Data_Model;
import ir.seoptimiz.Models.Questions_Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ir.seoptimiz.seoptimiz.Functions.db;

@RequestMapping("Api/v1")
@RestController
public class AjaxController 
{



    //New Question Api Start
    @RequestMapping(value = "NewQuestion",method = RequestMethod.GET)
    public Map<String,String> NewQuestion(HttpServletRequest request,@RequestParam("category") String category,@RequestParam("json") String json)
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            String js=Decript_Json(json);
            boolean b=db.INSERT_NEW_QUESTION(category, js);
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }    
    //New Question Api End
    

    


    //Get Question Categories Start
    //Api/v1/QuestionCategories
    @RequestMapping(value = "QuestionCategories")
    public ArrayList<Questions_Model> GET_Question_Categories(HttpServletRequest request)
    {
        ArrayList<Questions_Model> q=new ArrayList<>();
        if(Check_Cookie(request))
        {
            for(int i=0;i<db.Question_Categories.size();i++)
            {
                ArrayList<String> item=db.Question_Categories.get(i);
                q.add(new Questions_Model(Long.parseLong(item.get(0)),item.get(1),item.get(2)));
            }
            
        }
        return q;
    }
    //Get Question Categories End





    //Get User Data Start
    @RequestMapping(value = "SelectUserDataValues")
    public ArrayList<Data_Model> GET_USER_DATA_Table(HttpServletRequest request)
    {
        ArrayList<Data_Model> DataAry=new ArrayList();
        if(Check_Cookie(request))
        {
            for(int i=0;i<db.Data_Tb.size();i++)
            {
                ArrayList<String> dt=db.Data_Tb.get(i);
                DataAry.add(new Data_Model(Long.parseLong(dt.get(0)),Long.parseLong(dt.get(1)),dt.get(2),dt.get(3)));
            }
        }
        return DataAry;
    }
    //Get User Data End





    //Get Insert Data Start
    @RequestMapping(value = "InsertData",method = RequestMethod.GET)
    public Map<String,String> INSERT_DATA(HttpServletRequest request,@RequestParam("name") String title,@RequestParam("json") String json)
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            String js=Decript_Json(json);
            boolean b=db.INSERT_NEW_Data(title, js);
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }
    //Get Insert Data End




    //Get Delete Data Start
    //Api/v1/DeleteData
    @RequestMapping(value = "DeleteData",method = RequestMethod.GET)
    public Map<String,String> DELETE_DATA(HttpServletRequest request,@RequestParam("id") String id)    
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            boolean b=db.DELETE_Data(Long.parseLong(id));
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }
    //Get Delete Data End




    //Get Delete Data Start
    //Api/v1/UpdateData
    @RequestMapping(value = "UpdateData",method = RequestMethod.GET)
    public Map<String,String> UPDATE_DATA(HttpServletRequest request,@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("json") String js)    
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            boolean b=db.UPDATE_Data(Long.parseLong(id),name,Decript_Json(js));
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }
    //Get Delete Data End



    //Update Question Start
    @RequestMapping(value = "UpdateQuestion",method = RequestMethod.GET)
    public Map<String,String> UpdateQuestion(HttpServletRequest request,@RequestParam("category") String category,@RequestParam("json") String json,@RequestParam("id") String id)
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            String js=Decript_Json(json);
            Questions_Model q=new Questions_Model(Long.parseLong(id), category, js);
            boolean b=db.UPDATE_QUESTION(q);
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }    
    //Update Question End




    //Get Insert User Start
    //Api/v1/InsertUser
    @RequestMapping(value = "InsertUser",method = RequestMethod.GET)
    public Map<String,String> INSERT_USER(HttpServletRequest request,@RequestParam("name") String name,@RequestParam("family") String family,@RequestParam("username") String username,@RequestParam("password") String password)
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            boolean b=db.Insert_New_User(name, family, username, password);
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }
    //Get Insert User End





    //Get Insert User Start
    //Api/v1/UpdateUserPassword
    @RequestMapping(value = "UpdateUserPassword",method = RequestMethod.GET)
    public Map<String,String> UpdateUserPassword(HttpServletRequest request,@RequestParam("password") String password)
    {
        if(Check_Cookie(request))
        {
            HashMap<String,String> mp=new HashMap<>();
            boolean b=db.Update_User_Password(password);
            if(b)
            {
                mp.put("status", "success");
            }
            else
            {
                mp.put("status", "fuild");
            }
            return mp;
        }
        return null;
    }
    //Get Insert User End




    //Decript Json Start
    public String Decript_Json(String json)
    {
        java.util.Base64.Decoder decoder=Base64.getDecoder();
        String out=new String(decoder.decode(json));
        return out;
    }
    //Decript Json End





    //Check Cookies Start
    public boolean Check_Cookie(HttpServletRequest request)
    {
        Cookie []cookies=request.getCookies();
        String username="";
        String password="";

        for(int i=0;i<cookies.length;i++)
        {
            if(cookies[i].getName().equals("un"))
            {
                username=cookies[i].getValue();
            }
            if(cookies[i].getName().equals("up"))
            {
                password=cookies[i].getValue();
            }
        }

        if(username.equals("") || password.equals(""))
        {
            return false;
        }
        else
        {       
            return db.CHECK_USER(username,password);
        }
    }




}
