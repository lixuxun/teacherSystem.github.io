package teachersystem.entities;

public class Teacher {
    private Integer id;
    private String name;
    private Integer age;
    private String title;
    private String dept;
    private String course;
    private Integer number;
    private String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Teacher(Integer id, String name, Integer age, String title, String dept, String course, Integer number, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.title = title;
        this.dept = dept;
        this.course = course;
        this.number = number;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", title='" + title + '\'' +
                ", dept='" + dept + '\'' +
                ", course='" + course + '\'' +
                ", number=" + number +
                ", address='" + address + '\'' +
                '}';
    }
}
