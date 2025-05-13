package models.Dto;

public class CreatePerdoruesitDto {
    private String emriPerdoruesit;
    private String fjalekalimi;
    private String roli;

    public CreatePerdoruesitDto(String emriPerdoruesit, String fjalekalimi, String roli) {
        this.emriPerdoruesit = emriPerdoruesit;
        this.fjalekalimi = fjalekalimi;
        this.roli = roli;
    }

    public String getEmriPerdoruesit() {
        return emriPerdoruesit;
    }

    public void setEmriPerdoruesit(String emriPerdoruesit) {
        this.emriPerdoruesit = emriPerdoruesit;
    }

    public String getFjalekalimi() {
        return fjalekalimi;
    }

    public void setFjalekalimi(String fjalekalimi) {
        this.fjalekalimi = fjalekalimi;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }
}
