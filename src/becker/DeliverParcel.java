package becker;

import becker.robots.*;
import java.util.*;
public class DeliverParcel
{
    
   public static void derecha(Robot carro){
    for(int i=0;i<3;i++){
        carro.turnLeft();
    }
   }
   public static void girar180(Robot carro){
    for(int i=0;i<2;i++){
        carro.turnLeft();
    }
   }
   public static void parquear(Robot carro,int zona,int posicion){
       int posicion_guardar=0;
        for(int i=0;i<(2*zona)+1;i++){
            carro.move();
        }
       derecha(carro);
        for(int i=0;i<(5-posicion);i++){
         carro.move();
        }
        carro.turnLeft();
        carro.move();
   }
   public static void sacar_carro(Robot carro,int zona,int posicion){
        for(int i=0;i<2;i++){
            carro.turnLeft();
        }
        carro.move();
        derecha(carro);
        for(int i=0;i<(5-posicion);i++){
        carro.move();
        }
        carro.turnLeft();
        for(int i=0;i<(2*zona)+1;i++){
            carro.move();
        }
   }
   public static void mover_temporal(Robot carro,int zona,int posicion, int pos_temp){
        girar180(carro);
        carro.move();
        for(int i=0;i<3;i++){
            carro.turnLeft();
        }
        for(int i=0;i<(5-posicion);i++){
            carro.move();
        }
        carro.turnLeft();
        for(int i=0;i<2*zona;i++){
            carro.move();
        }
        derecha(carro);
        carro.move();
        derecha(carro);
        for(int i=0;i<pos_temp;i++){
            carro.move();
        }
        carro.turnLeft();
        carro.move();
   } 
    
   
   public static void devolver_a_posicion(Robot carro,int zona,int posicion,int pos_temp){
        girar180(carro);
        carro.move();
        derecha(carro);
        for(int i=0;i<pos_temp;i++){
            carro.move();
        }
        carro.turnLeft();
        carro.move();
        carro.turnLeft();
        for(int i=0;i<2*zona;i++){
            carro.move();
        }
        derecha(carro);
        for(int i=0;i<(5-posicion);i++){
            carro.move();
        }
        carro.turnLeft();
        carro.move();
   }
   
    public static boolean movimiento(Robot[][] robot,String placa,Zona_Parqueo parqueadero,double hora){
        if(parqueadero.existe_carro(placa)){
        Carro carro=parqueadero.carrito(placa);
        int pos=parqueadero.posicion_carro(carro);
        int zona=parqueadero.numero_zona_parqueo(carro);
        int zt=parqueadero.getIndice_temp();
        int c=parqueadero.obtener_contador_zona(zona);
        Robot[] parqueo_temp=new Robot[4];
        for(int i=c-1;i>pos;i--){
            mover_temporal(robot[zona][i],zona,i,zt);
            parqueo_temp[zt]=robot[zona][i];
            zt++;
        }
        sacar_carro(robot[zona][pos],zona,pos);
        for(int i=pos;i<c-1;i++){
            robot[zona][i]=robot[zona][i+1];
        }
        parqueadero.sacar_carro(carro,hora);
        int g=zt;
        for(int i=g;i>0;i--){
            devolver_a_posicion(parqueo_temp[i-1],zona,pos,i-1);
            pos++;
            zt--;
            }
        return true;
        }else{
            return false;    
        }
    }
   
   
      public static void crear(Robot[][] parqueo_r,Zona_Parqueo parqueadero,String placa,double hora,City parqueadero1){
       Carro carro= new Carro(placa,hora);
       parqueadero.agregar_carro_a_zona(carro);
       int posicion=parqueadero.posicion_carro(carro);
       int zona=parqueadero.numero_zona_parqueo(carro);
       parqueo_r[zona][posicion] = new Robot(parqueadero1, 5,6, Direction.WEST,0);
       parquear(parqueo_r[zona][posicion],zona,posicion);
   }


   public static void main(String[] args)
   {  
     Scanner datos= new Scanner(System.in);
    City parqueadero = new City();
    //parqueadero
    for(int i=0;i<5;i++){
        Wall paredes1 = new Wall(parqueadero, i,0 , Direction.WEST);
        Wall paredes2 = new Wall(parqueadero, i,2 , Direction.WEST);
        Wall paredes3 = new Wall(parqueadero, i,4 , Direction.WEST);
        Wall paredes4 = new Wall(parqueadero, i,5 , Direction.EAST);
        if(i<3){
        Wall parede5 = new Wall(parqueadero, 5,i*2 , Direction.NORTH);
            }
    }
    for(int i=1;i<5;i++){
        if(i<3){
        Wall paredes7 = new Wall(parqueadero, i+5,2 , Direction.WEST);     
        Wall paredes8 = new Wall(parqueadero, i+5,5 , Direction.EAST); 
        }
        if(i<4){
            Wall paredes6 = new Wall(parqueadero, 6,i+1 , Direction.NORTH); 

        }
        Wall paredes5 = new Wall(parqueadero, 7,i+1 , Direction.SOUTH);
    }
        //carros
        Robot[][] parqueo_r=new Robot[3][5];

        System.out.println("ingrese el valor por hora que desea cobrar en el parqueadero");
        double tarifa= datos.nextDouble();
        Zona_Parqueo parqueadero1= new Zona_Parqueo(tarifa);
        
        boolean ac=true;
    while(ac){
        System.out.println(""); 
        System.out.println(""); 
        System.out.println("1) ingresar carro");        
        System.out.println("2) sacar carro");
        System.out.println("3) obtener las ganancias");
        System.out.println("4) obtener placas de vehiculos de una zona");
        System.out.println("5) finalizar");
        int seleccion= datos.nextInt();
        switch(seleccion){
            case 1:
                System.out.println("ingrese la placa del carro ");
                datos.nextLine();
                String placa= datos.nextLine();
                System.out.println("ingrese la hora de entradadel carro ");
                
                double hora;
                hora=datos.nextDouble();
                crear(parqueo_r,parqueadero1,placa,hora,parqueadero); 

                break;
            case 2:
                System.out.println("ingrese la placa del vehiculo");
                datos.nextLine();
                String pla=datos.nextLine();
                System.out.println("ingrese la hora de salida");
               
                double salida=datos.nextDouble();
                boolean c=movimiento(parqueo_r,pla,parqueadero1,salida);
                if(c==false){
                    System.out.println("ese auto no esta parqueado");
                }
                break;
            case 3:
                System.out.println("las ganancias hasta este momento son " +parqueadero1.getGanancias());
                break;
            case 4:
                System.out.println("recuerde que las zonas son 3   2   1, acorde con la animacion");
                System.out.println("ingrese el numero de la zona");
                int z= datos.nextInt()-1;
                Creadora_Zonas mostrar=parqueadero1.getZona(z);
                for(int i=0;i<mostrar.getContador();i++){
                    System.out.println("placa posicion " + (i+1)+" : "+ mostrar.placa_i(i));
                }
                break;
            case 5:
                System.out.println("cierre la ventana de la animacion");
                break;
            default:
                System.out.println("cierre la ventana de la animacion");
        }
    }
   }
} 
