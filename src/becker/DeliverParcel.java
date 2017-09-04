package becker;

import becker.robots.*;

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
   
    public static void movimiento(Robot[][] robot,Carro carro,Zona_Parqueo parqueadero,double hora){
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
        for(int i=zt;i>0;i--){
            devolver_a_posicion(parqueo_temp[i-1],zona,pos,i-1);
            pos++;
            parqueo_temp[zt]=null;
            zt--;
            }

    }
   public void crear(Robot[][] parqueo_r,Zona_Parqueo parqueadero,String placa,double hora,City parqueadero1){
       Carro carro= new Carro(placa,7.50);
       parqueadero.agregar_carro_a_zona(carro);
       int posicion=parqueadero.posicion_carro(carro);
       int zona=parqueadero.numero_zona_parqueo(carro);
       parqueo_r[zona][posicion] = new Robot(parqueadero1, 5,6, Direction.WEST,0);
       parquear(parqueo_r[zona][posicion],zona,posicion);
   }


    
    
    
   
   
   public static void main(String[] args)
   {  
     
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
            Zona_Parqueo parqueadero1= new Zona_Parqueo(7000);
        
//
        Carro carro12= new Carro("ABeCq2221",7.50);
        parqueadero1.agregar_carro_a_zona(carro12);
        int posicion2=parqueadero1.posicion_carro(carro12);
        int zona2=parqueadero1.numero_zona_parqueo(carro12);
        parqueo_r[zona2][posicion2] = new Robot(parqueadero, 5,6, Direction.WEST,0);
        parquear(parqueo_r[zona2][posicion2],zona2,posicion2);
       
        
      for(int i=0;i<15;i++){
     crear(parqueo_r,parqueadero,placa,hora,parqueadero1);
      }
   }
} 
