package ir.seoptimiz.Models;

public class Data_Model 
{

    private long id;
    private long user_id;
    private String data;
    private String name;

    public Data_Model(long id, long user_id, String data, String name) {
        this.id = id;
        this.user_id = user_id;
        this.data = data;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
