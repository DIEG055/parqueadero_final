
package becker;
import becker.robots.*;

public class arreglo_robots {
private Robot[] robots;
private int contador;

    public arreglo_robots(Robot[] robots, int contador) {
        this.robots = new Robot[5];
        this.contador = 0;
    }
 
 
 
 public void agregar_robot(Robot robot){
     if(contador<5){
        robots[contador]=robot;
        contador++;
     }
 }
  public void eliminar_robot(int i){
    robots[i]=null;
    contador--;
 }
}
