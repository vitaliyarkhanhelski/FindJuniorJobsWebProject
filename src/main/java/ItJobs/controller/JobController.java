package ItJobs.controller;

import ItJobs.model.Job;
import ItJobs.repository.CityDAO;
import ItJobs.repository.TagDAO;
import ItJobs.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class JobController {

    private CityDAO cityDAO;
    private TagDAO tagDAO;
    private JobService jobService;

    public JobController(CityDAO cityDAO, TagDAO tagDAO, JobService jobService) {
        this.cityDAO = cityDAO;
        this.tagDAO = tagDAO;
        this.jobService = jobService;
    }

    @GetMapping
    public String jobSite(ModelMap map
            , @RequestParam(required = false) String newCity
            , @RequestParam(required = false) String newTag
    ) throws IOException {

        map.put("cities", cityDAO.getCities());
        map.put("tags", tagDAO.getTags());

        if ((newCity == null && newTag == null) || (newCity.equals("") && newTag.equals(""))) {
            map.put("data", null);
            return "index";
        }

        if (newTag.equals("")) {
            map.put("data", "provide");
            return "index";
        }

        if (newCity.equals("")) {
            map.put("myCity", " 'Poland',");
            map.put("myTag", " '" + newTag + "',");
            List<Job> jobs = jobService.getJobs("", newTag);
            if (jobs.isEmpty()) {
                map.put("data", "noResults");
            } else {
                map.put("jobs", jobs);
                map.put("data", "data");
            }
            return "index";
        }

        if (newCity != null && newTag != null) {
            String data = " ";
            map.put("data", "data");
            map.put("myCity", " '" + newCity + "',");
            map.put("myTag", " '" + newTag + "',");
            List<Job> jobs = jobService.getJobs(newCity, newTag);
            if (jobs.isEmpty()) {
                map.put("data", "noResults");
            } else {
                map.put("jobs", jobs);
                map.put("data", "data");
            }
        }
        return "index";
    }
}
