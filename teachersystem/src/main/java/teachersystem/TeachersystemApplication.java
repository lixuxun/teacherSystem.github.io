package teachersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //标注则不配置数据库
public class TeachersystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(TeachersystemApplication.class, args);
    }
}
