package ir.seoptimiz.Models;

public class Questions_Model 
{

    private long id;
    private String category;
    private String questions;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", category:'" + category + '\'' +
                ", questions:'" + questions + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Questions_Model(long id, String category, String questions) {
        this.id = id;
        this.category = category;
        this.questions = questions;
    }
}
