package xyz.lob.referenceofcomputerscience.content;

import android.app.Application;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.model.Post;


public class Content {

    private final List<Post> posts = new ArrayList<>();

    public enum Category {
        WIN(R.array.winPosts, "Windows"), LINUX(R.array.linuxPosts, "Linux"),
        NET(R.array.netPosts, "Сети"), PC(R.array.pcPosts, "Компоненты пк"),
        LOGIC(R.array.logicPosts, "Алгебра логики"), FORMULS(R.array.formulPosts, "Основные формулы"),
        GLAS(R.array.glasPosts, "Глоссарий");
        private final int id;
        private final String title;

        Category(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public int getArray() {
            return id;
        }

        public static Category getCategoryEnum(String title) {
            Category c = null;
            for (Category value : Category.values()) {
                c = value.title.equals(title) ? value : c;
            }
            return c;
        }
    }

    public List<Post> getBySearch(String query) {
        List<Post> allPost = getAllPost();
        List<Post> posts = allPost.stream().filter(post -> {
            return (post.getTitle().toLowerCase().contains(query.toLowerCase()) || post.getContent().toLowerCase().contains(query.toLowerCase()));
        }).collect(Collectors.toList());
        this.posts.clear();
        this.posts.addAll(posts);
        return posts;
    }

    public List<Post> getAllPost() {
        List<Post> allPost = new ArrayList<>();
        for (Category cat : Category.values())
            allPost.addAll(makeCategory(cat.title));
        posts.clear();
        posts.addAll(allPost);
        return allPost;
    }

    public List<Post> getForevers() {
        List<Post> allPost = getAllPost();
        List<Post> posts = allPost.stream().filter(post -> App.getInstance().getForevers().contains(post.getTitle())).collect(Collectors.toList());
        this.posts.clear();
        this.posts.addAll(posts);
        return posts;
    }

    public Post getPost(String title) {
        Post post = null;
        for (Post post1 : posts) {
            if (post1.getTitle().equals(title))
                post = post1;
        }
        return post;
    }

    public Post getPost(String cat, int id) {
        makeCategory(cat);
        return posts.get(id);
    }

    public Post getPost(int id) {
        return posts.get(id);
    }

    public Application a;

    public Content(Application application) {
        a = application;
    }

    public List<Post> makeCategory(String cat) {
        posts.clear();
        List<Post> posts = new ArrayList<>();

        TypedArray typedArrayPosts = a.getResources().obtainTypedArray(Category.getCategoryEnum(cat).getArray());
        int n = typedArrayPosts.length();
        Object[][] arrayPosts = new Object[n][];
        for (int i = 0; i < n; ++i) {
            int id = typedArrayPosts.getResourceId(i, 0);
            if (i < 3) arrayPosts[i] = a.getResources().getStringArray(id);
            else {
                TypedArray typedArrayImg = a.getResources().obtainTypedArray(id);
                arrayPosts[i] = new Object[arrayPosts[0].length];
                for (int j = 0; j < arrayPosts[0].length; ++j) {
                    arrayPosts[3][j] = a.getDrawable(typedArrayImg.getResourceId(j, 0));
                }
                typedArrayImg.recycle();
            }
        }
        typedArrayPosts.recycle();

        for (int i = 0; i < arrayPosts[0].length; ++i) {
            posts.add(new Post((String) arrayPosts[0][i], (String) arrayPosts[2][i], makeDetails((String) arrayPosts[1][i]), (Drawable) arrayPosts[3][i]).setForever(App.getInstance().getForevers().contains((String) arrayPosts[0][i])));
        }

        this.posts.addAll(posts);


        return this.posts;
    }

    private String makeDetails(String string) {
        StringBuilder builder = new StringBuilder();
        builder.append(string);
        builder.append("\n\nЧитать далее...");
        return builder.toString();
    }

}