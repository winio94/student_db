package pk.ssi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/student")
@Controller
public class StudentController {
    Map<Integer, StudentForm> studentList = new HashMap<Integer, StudentForm>();
    static int id = 0;
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView zapisz(StudentForm form, BindingResult errors,
            HttpServletRequest request, HttpServletResponse response){
        
        if(form.getId() == -1){
            id++;
            form.setId(id);
            studentList.put(id, form);
        } else {
            studentList.put(form.getId(), form);
        }
        
        Iterator iter = studentList.keySet().iterator();
        List<StudentForm> newMap = new ArrayList<StudentForm>();
        
        while(iter.hasNext()){
            Object key = iter.next();
            if(key != null){
                newMap.add(studentList.get(key));
            }
        }
        
        ModelMap map = new ModelMap();
        map.put("studenci", newMap);
        
        return new ModelAndView("pokaz", map);
    }
    
    @RequestMapping(value="/nowy", method=RequestMethod.GET)
    public ModelAndView nowyStudent(){
        StudentForm form = new StudentForm();
        ModelMap map = new ModelMap();
        map.put("student", form);
        return new ModelAndView("student", map);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String wyswietl(HttpServletRequest request){
        String widok = "";
        
        Iterator iter = studentList.keySet().iterator();
        List<StudentForm> newMap = new ArrayList<StudentForm>();
        
        while(iter.hasNext()){
            Object key = iter.next();
            if(key != null){
                newMap.add(studentList.get(key));
            }
        }
        
        request.setAttribute("studenci", newMap);
        widok = "pokaz";
        return widok;
    }
    
    @RequestMapping(value="/edytuj/{id}")
    public ModelAndView edytuj(@PathVariable String id){
        ModelMap map = new ModelMap();
        map.put("student", studentList.get(Integer.parseInt(id)));
        
        return new ModelAndView("student", map);
    }
    
    @RequestMapping(value="/usun/{id}")
    public String usun(@PathVariable String id, HttpServletRequest request){
        studentList.remove(Integer.parseInt(id));
                
        Iterator iter = studentList.keySet().iterator();
        List<StudentForm> newMap = new ArrayList<StudentForm>();
        
        while(iter.hasNext()){
            Object key = iter.next();
            if(key != null){
                newMap.add(studentList.get(key));
            }
        }
        
        request.setAttribute("studenci", newMap);
        return "pokaz";
    }
}
