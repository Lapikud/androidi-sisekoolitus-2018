package ee.ounapuu.yarc_yetanotherredditclient.model;

// very basic class for Reddit comments.
// TODO task BONUS 3: if you are really bored then implement comment threads. I dare ya. Note: this can and probably will be too time-consuming to do right now.
public class Comment {
    private String body;
    private Long score;
    private String author;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
