package Services;


import models.Perdoruesit;

public class SessionManager {
    private static SessionManager instance;
    //anetaret e sesionit
    private Perdoruesit perdoruesi;
    private String theme;

    private SessionManager(){
        this.theme = "default";
    }

    public static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(Perdoruesit perdoruesi){
        this.perdoruesi = perdoruesi;
    }

    public Perdoruesit getCurrentUser(){
        return this.perdoruesi;
    }
}
