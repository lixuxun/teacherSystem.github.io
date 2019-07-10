package teachersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teachersystem.dao.TeacherDao;
import teachersystem.entities.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    TeacherDao teacherDao;

    //获取全部老师
    @GetMapping("/getTeachers")
    public String getTeachers(HttpServletRequest request) {
        Collection teachers = teacherDao.getAll();
        request.setAttribute("teachers", teachers);
        System.out.println(teachers);
        return "/list.html";
    }

    //通过ID或名字获取单个老师
    @GetMapping("/getTeacherByIdOrName")
    public String getTeacherByIdOrName(@RequestParam("IdOrName") String IdOrName, HttpServletRequest request) {
//             Teacher teacher = teacherDao.get(id);
//        session.setAttribute("teacher",teacher);//页面获取
//        System.out.println("teacher"+ teacher);
//            return "redirect:/trace.html";
        System.out.println(IdOrName);
        //   ArrayList<Teacher> teachers = null;
        Collection teachers = null;
        if (!parseInt(IdOrName)) {
            String Name = IdOrName;
            teachers = teacherDao.getByName(Name);
            System.out.println("Name  " + IdOrName);
        } else if (parseInt(IdOrName)) {
            Integer id = Integer.parseInt(IdOrName);
            teachers = teacherDao.getById(id);
            System.out.println("Int  " + id);
        }

//        Teacher teacher = teacherDao.getById(IdOrName);
//        if (teacher != null) {
//            teachers = new ArrayList<Teacher>();
//            teachers.add(teacher);
//        }
        request.setAttribute("teachers", teachers);//页面获取
        System.out.println("teacher" + teachers);
        return "trace";
    }


    //通过关键字获取单个老师
    @GetMapping("/getTeacherByKeyWord")
    public String getTeacherByKeyWord(@RequestParam("KeyWord") String KeyWord, HttpServletRequest request) {
        System.out.println("KeyWord" + KeyWord + KeyWord.length());
        Collection teachers = teacherDao.getByKeyWord(KeyWord);
        ;

        System.out.println("teacher" + teachers);
        request.setAttribute("teachers", teachers);//页面获取

        return "trace";
    }


    //添加
//    @PostMapping("/add")
//    @ResponseBody
//    public String add(Teacher teacher) {
//
//        teacherDao.insertTeacher(teacher);
//        System.out.println("teacher " + teacher);
//
//        return "<a href=\"/menu.html\">添加成功,手动返回菜单界面......</a>";
//    }
    //添加
    @PostMapping("/add")
    @ResponseBody
    public String add(String id, String name, String age, String title, String dept, String course, String number, String address, HttpServletRequest request, HttpSession session) {
        if (!parseInt(id)) {

            session.setAttribute("msgId", "工号必须为数字字符");
            return "<a style=\"color: red\" href=\"/add.html\">添加失败,所填信息有误,请手动返回添加界面......</a>";
            //       return "add";
        } else if (!parseInt(age)) {
            session.setAttribute("msgAge", "年龄必须为数字字符");
            return "<a style=\"color: red\" href=\"/add.html\">添加失败,所填信息有误,请手动返回添加界面......</a>";
        } else if (!parseInt(number)) {
            session.setAttribute("msgNumber", "联系号码必须为数字字符");
            return "<a style=\"color: red\" href=\"/add.html\">添加失败,所填信息有误,请手动返回添加界面......</a>";
        } else if (title.equals("") || dept.equals("") || course.equals("")) {
            session.setAttribute("msgTDC", "职称、属系或所授课程不能为空");
            return "<a style=\"color: red\" href=\"/add.html\">添加失败,所填信息有误,请手动返回添加界面......</a>";
        }
//        teacherDao.insertTeacher(teacher);
        else {
            session.removeAttribute("msgId");
            session.removeAttribute("msgAge");
            session.removeAttribute("msgNumber");
            session.removeAttribute("msgTDC");
            //插入数据
            Teacher teacher = new Teacher(Integer.parseInt(id), name, Integer.parseInt(age), title, dept, course, Integer.parseInt(number), address);
            System.out.println("addTeacher :" + teacher);
            try {
                teacherDao.insertTeacher(teacher);

            } catch (Exception e) {
                System.out.println("插入失败 " + teacher);
                return "<a style=\"color: red\" href=\"/menu.html\">添加信息失败，该用户ID已经存在！,请手动返回菜单界面......</a>";
            }
            return "<a href=\"/menu.html\">添加信息成功,请手动返回菜单界面......</a>";
        }
    }


    //初始化addTeacher页面
    @GetMapping("/addTeacher")
    public String initAddTeacher(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("msgId");
        session.removeAttribute("msgAge");
        session.removeAttribute("msgNumber");
        session.removeAttribute("msgTDC");
        return "add";
    }

    //跳转到修改页面
    @GetMapping("/updateTeacher")
    public String update(@RequestParam("id") Integer id, HttpSession session, HttpServletRequest request) {
        List teachers = teacherDao.getById(id);

        if (teachers.size() == 0) {

            return "error";
        }

        session.setAttribute("oldId", id);
        request.setAttribute("updateTeacher", teachers.get(0));
        return "add";
    }

    //实现修改
    @PutMapping("/add")
    @ResponseBody
    public String update(Teacher teacher, HttpServletRequest request) {
        System.out.println("update:" + teacher);
        System.out.println("oldId:" + request.getSession().getAttribute("oldId"));
        int updateSign = teacherDao.updateTeacher(teacher, (Integer) request.getSession().getAttribute("oldId"));
        return "<a href=\"/menu.html\">修改信息成功,手动返回菜单界面......</a>";
    }

    //删除
    @ResponseBody
    @GetMapping("/deleteTeacher")
    public String delete(@RequestParam("id") String id, HttpSession session, HttpServletRequest request) {
        if (!parseInt(id)) {
            return "<a style=\"color: red\" href=\"/delete.html\">ID不存在，请输入正确的ID！,手动返回删除界面......</a>";
        }
        int deleteSign;
        try {
            deleteSign = teacherDao.deleteDeptById(Integer.parseInt(id));

        } catch (Exception e) {

            return "<a style=\"color: red\" href=\"/delete.html\">ID不存在，请输入正确的ID！,手动返回删除界面......</a>";
        }
        System.out.println("deleteSign:" + deleteSign);
        if (deleteSign == 0) {
            return "<a style=\"color: red\" href=\"/delete.html\">ID不存在，请输入正确的ID号！,手动返回删除界面......</a>";
        }
        return "<a href=\"/menu.html\">删除信息成功,手动返回菜单界面......</a>";
    }

    public boolean parseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
