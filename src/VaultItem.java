public class VaultItem {
    private String title;
    private String username;
    private String password;
    private String url;

    public VaultItem(String item_title, String item_username, String item_password, String item_url){
        this.title = item_title;
        this.username = item_username;
        this.password = item_password;
        this.url = item_url;
    }

    public String getTitle(){
        return this.title;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUrl(){
        return this.url;
    }
}
