package ir.seoptimiz.seoptimiz.Controllers;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ir.seoptimiz.seoptimiz.Functions.db;

@Controller
@RequestMapping("")
public class MainController
{



    @RequestMapping(value = "Login")
    public ModelAndView Login()
    {
        ModelAndView mod=new ModelAndView();
        mod.setViewName("login");
        return mod;
    }



    @RequestMapping(value = "")
    public String Empty()
    {
        return "redirect:Login";
    }



    @RequestMapping(value = "Login",method = RequestMethod.POST)
    public String Login(@RequestParam String username,@RequestParam String password,HttpServletResponse response)
    {
        String mad="redirect:Login";

        if(db.CHECK_USER(username,password))
        {
            Cookie username_cookie=new Cookie("un", username);
            Cookie password_cookie=new Cookie("up", password);
            response.addCookie(username_cookie);
            response.addCookie(password_cookie);            
            mad="redirect:Dashboard";
        }
        else
        {
            mad="redirect:Login";
        }

        return mad;
    }










    /*****************Dashboard******************* */
    @RequestMapping(value = "Dashboard")
    public ModelAndView Dashboard(HttpServletRequest request)
    {
        ModelAndView mad=new ModelAndView();

        if(Check_Cookie(request))
        {
            mad.setViewName("dashboard");
        }
        else
        {
            mad.setViewName("redirect:Login");
        }
        
        return mad;
    }

    @RequestMapping(value = "AllUsers")
    public ModelAndView AllUsers(HttpServletRequest request)
    {
        ModelAndView mad=new ModelAndView();
        if(Check_Cookie(request))
        {
            ArrayList<String> user_data=db.Users.get(0);
            mad.addObject("id", user_data.get(0));
            mad.addObject("name", user_data.get(1));
            mad.addObject("family", user_data.get(2));
            mad.addObject("username", user_data.get(3));
            mad.setViewName("users");
        }
        else
        {
            mad.setViewName("redirect:Login");
        }
        return mad;
    }

    @RequestMapping(value = "NewSeo")
    public ModelAndView NewSeo(HttpServletRequest request)
    {        
        ModelAndView mad=new ModelAndView();
        if(Check_Cookie(request))
        {
            db.GET_QUESTIONS_CATEGORY();
            if(db.Question_Categories.size()<=0)
            {
                mad.addObject("data_is_exist", "false");
            }
            else
            {
                mad.addObject("data_is_exist", "true");
            }
            mad.setViewName("newseo");
        }
        else
        {
            mad.setViewName("redirect:Login");
        }
        return mad;
    }

    @RequestMapping(value = "AllSeo")
    public ModelAndView AllSeo(HttpServletRequest request)
    {   
        ModelAndView mad=new ModelAndView();
        if(Check_Cookie(request))
        {
            db.GET_SELECT_DATA_TB();

            if(db.Data_Tb.size()>0)
            {
                mad.addObject("data_exist", "true");
            }
            else
            {
                mad.addObject("data_exist", "false");
            }

            mad.setViewName("allseo");
        }
        else
        {
            mad.setViewName("redirect:Login");
        }
        return mad;
    }

    @RequestMapping(value = "NewQuestion")
    public ModelAndView NewQuestion(HttpServletRequest request)
    {   
        ModelAndView mad=new ModelAndView();
        if(Check_Cookie(request))
        {
            mad.setViewName("newquestion");
        }
        else
        {
            mad.setViewName("redirect:Login");
        }
        return mad;
    }

    @RequestMapping(value = "UpdateQuestion")
    public ModelAndView UpdateQuestion(HttpServletRequest request)
    {   
        ModelAndView mad=new ModelAndView();
        if(Check_Cookie(request))
        {
            db.GET_QUESTIONS_CATEGORY();
            mad.setViewName("updatequestion");
        }
        else
        {
            mad.setViewName("redirect:Login");
        }
        return mad;
    }
    /*****************Dashboard******************* */













    //Exit From Account Start
    @RequestMapping("Exit")
    public String Exit(HttpServletResponse response)
    {
        Cookie username_cookie=new Cookie("un", null);
        Cookie password_cookie=new Cookie("up", null);
        response.addCookie(username_cookie);
        response.addCookie(password_cookie);      
        return "redirect:";
    }
    //Exit From Account End









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
