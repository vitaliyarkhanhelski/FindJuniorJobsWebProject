package ItJobs;

import ItJobs.model.Job;
import ItJobs.repository.CityDAO;
import ItJobs.repository.TagDAO;
import ItJobs.service.JobService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ItJobsApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ItJobsApplication.class, args);

    }
}
