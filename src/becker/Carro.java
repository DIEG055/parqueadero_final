package becker;


public class Carro {
 // atributos
  private String placa;
  private double tiempo;
  private int zona;
  private int posicion;
//atributos

    public Carro(String placa, double tiempo) {
        this.placa = placa;
        this.tiempo= tiempo;
    }

    public String getPlaca() {
        return placa;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getZona() {
        return zona;
    }

    public int getPosicion() {
        return posicion;
    }
  
    
    
    
}
