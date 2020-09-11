package ItJobs.service;

import ItJobs.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class JobService {


    private static List<Job> jobs = new ArrayList<>();
    private static final String EXPERIENCE = "junior";


    public List<Job> getJobs(String city, String tag) throws IOException {
        if (!jobs.isEmpty()) jobs.clear();

        pracujPl(city, tag, EXPERIENCE);

        buldog(city, tag, EXPERIENCE);

        noFluffJobs(city, tag, EXPERIENCE);

        return jobs;
    }

    private static void noFluffJobs(String city, String tag, String experience) throws IOException {
        String url;
        if (!city.equals(""))
            url = "https://nofluffjobs.com/pl/jobs/" + city + "/java?criteria=city%3D" + city + "%20seniority%3D" + experience + "%20" + tag + "&page=1";
        else url = "https://nofluffjobs.com/pl/jobs/java?criteria=seniority%3D" + experience + "%20" + tag + "&page=1";

        if (Jsoup.connect(url).get().getElementsByClass("text-white font-weight-bold").first() == null) {

            List<Element> names = Jsoup.connect(url).get().getElementsByClass("posting-title__position");

            Element element = Jsoup.connect(url).get().getElementsByTag("nfj-postings-list").first();
            List<Element> elements = element.getElementsByTag("a");

            List<Element> links = new ArrayList<>();
            for (int i = 1; i <= elements.size(); i++) {
                if (i % 2 == 0) links.add(elements.get(i - 1));
            }

            for (int i = 0; i < names.size(); i++) {
                jobs.add(new Job(names.get(i).text(), "https://nofluffjobs.com/" + links.get(i).attr("href")));
            }
        }
    }


    private static void buldog(String city, String tag, String experience) throws IOException {
        String url;
        if (!city.equals(""))
            url = "https://bulldogjob.pl/companies/jobs/s/city," + city + "/skills," + tag + "/experience_level," + experience;
        else url = "https://bulldogjob.pl/companies/jobs/s/skills," + tag + "/experience_level," + experience;
        List<Element> name = Jsoup.connect(url).get().getElementsByClass("title");
        Element element = Jsoup.connect(url).get().getElementsByClass("results-list list-unstyled content").first();
        List<Element> list = element.getElementsByTag("a");

        for (int i = 0; i < name.size(); i++) {
            jobs.add(new Job(name.get(i).text(), list.get(i).attr("href")));
        }
    }


    private static void pracujPl(String city, String tag, String experience) throws IOException {
        String url;
        if (!city.equals(""))
            url = "https://www.pracuj.pl/praca/" + tag + "%20" + experience + ";kw/" + city + ";wp?rd=30";
        else url = "https://www.pracuj.pl/praca/" + tag + "%20" + experience + ";kw?rd=30";

        List<Element> data = Jsoup.connect(url).get().getElementsByClass("results__list-container-item");

        for (int i = 0; i < data.size() - 1; i++) {
            String href = data.get(i).getElementsByClass("offer-details__title-link").attr("href");

            if (!href.equals(""))
                jobs.add(new Job(data.get(i).getElementsByClass("offer-details__title-link").text(),
                        href));
        }
    }
}