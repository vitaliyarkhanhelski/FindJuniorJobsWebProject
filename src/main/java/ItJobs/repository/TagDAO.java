package ItJobs.repository;

import ItJobs.model.Tag;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDAO {

    private static final List<Tag> TAGS = new ArrayList<>();

    static {
        initData();
    }

    private static void initData() {
        TAGS.add(new Tag("java","Java"));
        TAGS.add(new Tag("python","Python"));
        TAGS.add(new Tag("javascript","JavaScript"));
    }

    public List<Tag> getTags(){
        return TAGS;
    }


}
