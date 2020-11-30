package xyz.lob.referenceofcomputerscience.content;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import xyz.lob.referenceofcomputerscience.R;


public class Content {

    private static final Content content = new Content();
    private List<Post> posts = new ArrayList<Post>();
    private Content() {
    }

    private enum Category{
        WIN("winPost", "Windows"), LINUX("linPost", "Linux"),
        NET("netPost", "Сети"), PC("pcPost", "Компоненты пк"),
        LOGIC("logicPost", "Алгебра логики"), FORMULS("formulsPost",  "Основные формулы"),
        GLAS("glasPost", "Глоссарий");
        private String arrayName, title;

        Category(String arrayName, String title){
            this.arrayName = arrayName;
            this.title = title;
        }
        public String getArray(){return arrayName;}
        public static Category getCategoryEnum(String title){
            Category c = null;
            //Log.e("values", Category.values().length + "");
            for (Category value : Category.values()) {
                //Log.e("for", value.title);
                   c = value.title.equals(title) ? value: c;
            }
            return c;
        }
    }

    public static Post getPost(String cat, int id){
        return content.posts.get(id);
    }
    public static List<Post> makeCategory(String cat){
        Log.e("qw",Category.getCategoryEnum(cat).getArray() + " " + cat);
//        List<Post> posts = new ArrayList<>();
//        TypedArray typedArray = Resources.getSystem().obtainTypedArray(R.array.winPosts);
//        int n = typedArray.length();
//        String[][] array = new String[n][];
//        for (int i = 0; i < n; ++i) {
//            int id = typedArray.getResourceId(i, 0);
//            array[i] = Resources.getSystem().getStringArray(id);
//            posts.add(content.createItem(i));
//        }
//        typedArray.recycle();
        content.posts.add(new Post("w",R.string.large_text, "2", R.drawable.fon));
       // content.posts.addAll(posts);
        return content.posts;
    }

    private String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class Post {
        public final String title;
        public final int content;
        public final String details;
        public final int img;

        public Post(String title, int content, String details, int img) {
            this.title = title;
            this.content = content;
            this.details = details;
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public int getContent() {
            return content;
        }

        public String getDetails() {
            return details;
        }

        public int getImg() {
            return img;
        }

        @Override
        public String toString() {
            return content + "";
        }
    }
}