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
        
        
        Carro carro11= new Carro("ABC123",7.30);
        Zona_Parqueo parqueadero1= new Zona_Parqueo(7000);
        parqueadero1.agregar_carro_a_zona(carro11);
        parqueo_r[parqueadero1.numero_zona_parqueo(carro11)][parqueadero1.posicion_carro(carro11)] = new Robot(parqueadero, 5,6, Direction.WEST,0);
        carro11.setPosicion(parqueadero1.posicion_carro(carro11));
        carro11.setZona(parqueadero1.numero_zona_parqueo(carro11));
        parquear(parqueo_r[carro11.getZona()][carro11.getPosicion()],carro11.getZona(),carro11.getPosicion());
       

        Carro carro12= new Carro("ABC124",7.30);
        parqueadero1.agregar_carro_a_zona(carro12);
        parqueo_r[parqueadero1.numero_zona_parqueo(carro12)][parqueadero1.posicion_carro(carro12)] = new Robot(parqueadero, 5,6, Direction.WEST,0);
        carro12.setPosicion(parqueadero1.posicion_carro(carro12));
        carro12.setZona(parqueadero1.numero_zona_parqueo(carro12));
        parquear(parqueo_r[carro12.getZona()][carro12.getPosicion()],carro12.getZona(),carro12.getPosicion());

       
        Carro carro13= new Carro("ABC121",7.30);
        parqueadero1.agregar_carro_a_zona(carro13);
        parqueo_r[parqueadero1.numero_zona_parqueo(carro13)][parqueadero1.posicion_carro(carro13)] = new Robot(parqueadero, 5,6, Direction.WEST,0);
        int posicion3=parqueadero1.posicion_carro(carro13);
        int zona3=parqueadero1.numero_zona_parqueo(carro13);
        parquear(parqueo_r[zona3][posicion3],zona3,posicion3);
       
         
        Carro carro14= new Carro("ABC221",7.50);
        parqueadero1.agregar_carro_a_zona(carro14);
        int posicion4=parqueadero1.posicion_carro(carro14);
        int zona4=parqueadero1.numero_zona_parqueo(carro14);
        parqueo_r[zona4][posicion4] = new Robot(parqueadero, 5,6, Direction.WEST,0);
        parquear(parqueo_r[zona4][posicion4],zona4,posicion4);
       

        Carro carro15= new Carro("ABddC221",7.30);
        parqueadero1.agregar_carro_a_zona(carro15);
        int posicion5=parqueadero1.posicion_carro(carro15);
        int zona5=parqueadero1.numero_zona_parqueo(carro15);
        parqueo_r[zona5][posicion5]= new Robot(parqueadero, 5,6, Direction.WEST,0);
        parquear(parqueo_r[zona5][posicion5],zona5,posicion5);
       
        
        Carro carro16= new Carro("ABddC781",7.30);
        parqueadero1.agregar_carro_a_zona(carro16);
        int posicion6=parqueadero1.posicion_carro(carro16);
        int zona6=parqueadero1.numero_zona_parqueo(carro16);
        parqueo_r[zona6][posicion6]= new Robot(parqueadero, 5,6, Direction.WEST,0);
        parquear(parqueo_r[zona6][posicion6],zona6,posicion6);
       
        
        Carro carro17= new Carro("ABddC201121",7.30);
        parqueadero1.agregar_carro_a_zona(carro17);
        int posicion7=parqueadero1.posicion_carro(carro17);
        int zona7=parqueadero1.numero_zona_parqueo(carro17);
        parqueo_r[zona7][posicion7]= new Robot(parqueadero, 5,6, Direction.WEST,0);
        parquear(parqueo_r[zona7][posicion7],zona7,posicion7);  


    movimiento(parqueo_r,carro14,parqueadero1,10.5);
    movimiento(parqueo_r,carro11,parqueadero1,8.5);

        Carro carro18= new Carro("ABd74C2015121",7.30);
        parqueadero1.agregar_carro_a_zona(carro18);
        int posicion8=parqueadero1.posicion_carro(carro18);
        int zona8=parqueadero1.numero_zona_parqueo(carro18);
        parqueo_r[zona8][posicion8]= new Robot(parqueadero, 5,6, Direction.WEST,0);
        parquear(parqueo_r[zona8][posicion8],zona8,posicion8);         


   }
} 
