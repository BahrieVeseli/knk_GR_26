package models.Dto;

public class CreateDestinacionetDto {
    private String emriStacionit;

    public CreateDestinacionetDto(String emriStacionit) {
        this.emriStacionit = emriStacionit;
    }

    public String getEmriStacionit() {
        return emriStacionit;
    }

    public void setEmriStacionit(String emriStacionit) {
        this.emriStacionit = emriStacionit;
    }
}
