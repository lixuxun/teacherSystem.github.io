package teachersystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import teachersystem.entities.Teacher;

import java.util.Collection;
import java.util.List;

@Mapper
@Component
public interface TeacherDao {

    //    private static Map<Integer, Teacher> teacher=null;
//
//    static {
//        teacher=new HashMap<Integer, Teacher>();
//        teacher.put(1001,new Teacher(1001,"张三", 24,"professor","computer","数据库",0745123,"珠海"));
//    }
//    public Teacher get(Integer id){
//
//        return teacher.get(id);
//    }
    //查询全部老师
    @Select("select * from teacher order by id ")
    public Collection<Teacher> getAll();

    //查询单个老师
    @Select("select * from teacher where id LIKE concat(concat('%',#{id}),'%')")
    public List<Teacher> getById(Integer id);


    //通过名字查询单个或多个老师
    @Select("select * from teacher where name LIKE concat(concat('%',#{Name}),'%')")
    public Collection<Teacher> getByName(String Name);

    //通过ID查询单个老师
    @Select("select * from teacher where title=#{KeyWord} or dept=#{KeyWord} or course=#{KeyWord}")
    public List<Teacher> getByKeyWord(String KeyWord);


    //删除单个老师
    @Delete("delete from teacher where id=#{id}")
    public int deleteDeptById(Integer id);

    //添加老师
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into teacher values(#{id},#{name},#{age},#{title},#{dept}," +
            "#{course},#{number},#{address})")
    public int insertTeacher(Teacher teacher);

    //更新老师信息
    @Update("update teacher set id=#{teacher.id},name=#{teacher.name},age=#{teacher.age},title=#{teacher.title},dept=#{teacher.dept},course=#{teacher.course}," +
            "number=#{teacher.number},address=#{teacher.address} where id=#{oldId}")//注意不能用#{id}
    public int updateTeacher(@Param("teacher") Teacher teacher, @Param("oldId") Integer oldId);
}
