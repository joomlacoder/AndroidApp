package xyz.lob.referenceofcomputerscience.content;

import android.app.Application;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import xyz.lob.referenceofcomputerscience.R;


public class Content {

    private final List<Post> posts = new ArrayList<>();

    private enum Category{
        WIN("winPost", "Windows"), LINUX("linuxPost", "Linux"),
        NET("netPost", "Сети"), PC("pcPost", "Компоненты пк"),
        LOGIC("logicPost", "Алгебра логики"), FORMULS("formulPost",  "Основные формулы"),
        GLAS("glasPost", "Глоссарий");
        private final String arrayName;
        private final String title;

        Category(String arrayName, String title){
            this.arrayName = arrayName;
            this.title = title;
        }
        public String getArray(){return arrayName;}
        public static Category getCategoryEnum(String title){
            Category c = null;
            for (Category value : Category.values()) {
                   c = value.title.equals(title) ? value: c;
            }
            return c;
        }
    }

    public Post getPost(String cat, int id){
        makeCategory(cat);
        return posts.get(id);
    }

    public Post getPost(int id){
        return posts.get(id);
    }

    public Application a;
    public Content(Application application){
        a=application;
    }

    public List<Post> makeCategory(String cat){
        List<Post> posts = new ArrayList<>();

        String[] postTitles = a.getResources().getStringArray(R.array.glasPostTitle);
        String[] postConteents = a.getResources().getStringArray(R.array.glasPostText);
        String[] postDetailds = a.getResources().getStringArray(R.array.glasPostDetal);
        TypedArray typedArray = a.getResources().obtainTypedArray(R.array.glasPostImg);
        int n = typedArray.length();
        Drawable[] postImgs = new Drawable[n];
        for (int i = 0; i < n; ++i){
            postImgs[i] = a.getDrawable(typedArray.getResourceId(i, 0));
        }
        typedArray.recycle();

        posts.add(new Post(postTitles[1],postConteents[1], postDetailds[1], postImgs[1]));
        this.posts.addAll(posts);
        return posts;
    }

    private String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}