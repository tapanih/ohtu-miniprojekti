package logic;

import java.util.List;

public interface Bookmark {

    List<String> getTags();

    String getTitle();

    int getId();
    
    BookmarkType getType();

}
