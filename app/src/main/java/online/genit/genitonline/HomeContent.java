package online.genit.genitonline;

import android.graphics.drawable.Drawable;

import java.io.UnsupportedEncodingException;

public class HomeContent {
    private int icon;
    private String userName;
    private String createDate;
    private String content;
    private boolean type;

    public HomeContent(){

    }

    public HomeContent(int icon, String userName, String createDate, String content, boolean type){
        this.icon = icon;
        this.userName = userName;
        this.createDate = createDate;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;

    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
