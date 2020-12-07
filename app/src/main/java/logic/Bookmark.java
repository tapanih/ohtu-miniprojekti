package logic;

import java.util.List;

public interface Bookmark {

    List<String> getTags();
    
    void addTag(String tag);

    String getTitle();

    int getId();
    
    BookmarkType getType();

}
