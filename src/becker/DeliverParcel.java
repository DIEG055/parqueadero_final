package becker;

import becker.robots.*;

public class DeliverParcel
{
   public static void parquear(Robot carro,int zona,int posicion){
       int posicion_guardar=0;
        for(int i=0;i<(2*zona)+1;i++){
            carro.move();
        }
        for(int i=0;i<3;i++){
            carro.turnLeft();
        }
        for(int i=0;i<(5-posicion);i++){
        carro.move();
        }
        carro.turnLeft();
        carro.move();
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
        //carros
        Robot carro1 = new Robot(parqueadero, 5,6, Direction.WEST,0);
        Carro carro11= new Carro("ABC123",7.30);
        Zona_Parqueo parqueadero1= new Zona_Parqueo();
        parqueadero1.agregar_carro_a_zona(carro11);
       
        int posicion=parqueadero1.posicion_carro(carro11);
        int zona=parqueadero1.numero_zona_parqueo(carro11);
        
        //System.out.println("posicion: " + posicion);     
        System.out.println("zona: " + zona);
        System.out.println("posicion: " + posicion);
        parquear(carro1,zona,posicion);
        
        
        //carros
        Robot carro2 = new Robot(parqueadero, 5,6, Direction.WEST,0);
        Carro carro12= new Carro("ABC124",7.30);
        parqueadero1.agregar_carro_a_zona(carro12);
       
        int posicion_2=parqueadero1.posicion_carro(carro12);
        int zona_2=parqueadero1.numero_zona_parqueo(carro12);
        
        
        parquear(carro2,zona_2,posicion_2);
        
        
        //carros
        Robot carro3 = new Robot(parqueadero, 5,6, Direction.WEST,0);
        Carro carro13= new Carro("ABC121",7.30);
        parqueadero1.agregar_carro_a_zona(carro13);
       
        int posicion_3=parqueadero1.posicion_carro(carro13);
        int zona_3=parqueadero1.numero_zona_parqueo(carro13);
        
        
        parquear(carro3,zona_3,posicion_3);
        
        
            
        Robot carro4 = new Robot(parqueadero, 5,6, Direction.WEST,0);
        Carro carro14= new Carro("ABC221",7.30);
        parqueadero1.agregar_carro_a_zona(carro14);
       
        int posicion_4=parqueadero1.posicion_carro(carro14);
        int zona_4=parqueadero1.numero_zona_parqueo(carro14);
        
        
        parquear(carro4,zona_4,posicion_4);
        
   }
} 