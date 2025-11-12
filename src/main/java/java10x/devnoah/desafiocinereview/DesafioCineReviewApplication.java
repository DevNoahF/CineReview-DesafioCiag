package java10x.devnoah.desafiocinereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"java10x.devnoah.desafiocinereview", "repository", "model", "DTO", "mapper"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"model"})
public class DesafioCineReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioCineReviewApplication.class, args);
    }

}
