package modelo;

public class Ciudad {

    private String ciudad;
    private int distancia;
    private int tiempo;

    public Ciudad(String ciudad, int distancia, int tiempo) {
        this.ciudad = ciudad;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String toString(){ return ciudad; }
}
