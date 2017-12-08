package kz.greetgo.education.controller.model;

public class LoginInfo {
    String id;
    String type_id;
    public LoginInfo(String id,String type_id){
        this.id = id;
        this.type_id = type_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
