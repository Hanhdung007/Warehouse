package warehouse.exam.demo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
//        LocalDateTime ldt = LocalDateTime.now();
//        Instant instant = ldt.toInstant(ZoneOffset.UTC);
//        Date date = Date.from(instant);
//        System.out.println(date);
    }

}
