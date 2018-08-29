package naivebayes;

public class Data {

    private String cuaca;
    private String temperature;
    private String angin;
    private String olahraga;

    public Data() {
    }

    public Data(String cuaca, String temperature, String angin, String olahraga) {
        this.cuaca = cuaca;
        this.temperature = temperature;
        this.angin = angin;
        this.olahraga = olahraga;
    }

    public String getCuaca() {
        return cuaca;
    }

    public void setCuaca(String cuaca) {
        this.cuaca = cuaca;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAngin() {
        return angin;
    }

    public void setAngin(String angin) {
        this.angin = angin;
    }

    public String getOlahraga() {
        return olahraga;
    }

    public void setOlahraga(String olahraga) {
        this.olahraga = olahraga;
    }
}
