package becker;


public class Zona_Parqueo {
    private Creadora_Zonas[] zona_total;
    private int indice_temp;
    private  Carro[] zona_temporal;
    private double tarifa;
    private double ganancias=0;
    //metodos
    public int getIndice_temp() {
        return indice_temp;
    }

    public int obtener_contador_zona(int i){
       return zona_total[i].getContador();
    }
    public Creadora_Zonas getZona(int i){
        return zona_total[i];
    }

    public double getGanancias() {
        return ganancias;
    }
    
    public Zona_Parqueo(double tarifa){    
        this.zona_total= new Creadora_Zonas[3];
        for(int i=0;i<3;i++){
            zona_total[i]= new Creadora_Zonas();
        }
        this.zona_temporal= new Carro[4];
        this.tarifa=tarifa;
    }

    public int mejor_zona() {
        int c=zona_total[0].getContador();
        int j=0;
        for(int i=1;i<3;i++){
            if(zona_total[i].getContador() < c){
                c=zona_total[i].getContador();
                j=i;
                break;
            } 
        }
        return j;
    }
    
    public void agregar_carro_a_zona(Carro carro){
        int k=mejor_zona();
        if(zona_total[k].getContador()<5){
            zona_total[k].agregar_carro(carro);
        }
    }
    
    public int posicion_carro(Carro carro){
        int p=6;
        for(int i=0;i<3;i++){
            if(zona_total[i].parqueado_en_esta_zona(carro)){
                p =zona_total[i].posicion_carro_guardado(carro);
                break;
            }
        }
    return p;
    }
    
    public int numero_zona_parqueo(Carro carro){
        int j=4;   
        for(int i=0;i<3;i++){
                if(zona_total[i].parqueado_en_esta_zona(carro)){
                    j=i;
                    break;
                }
                
            } 
    return j;
    }
    public void pagar(Carro carro, double hora){
        double h= hora-carro.getTiempo();
        h=h*tarifa;
        this.ganancias=h+this.ganancias;
    }
        
    public void sacar_carro(Carro carro, double tiempo){
        int num_zona = numero_zona_parqueo(carro);
        int pos_car=zona_total[num_zona].posicion_carro_guardado(carro);
        int cont=zona_total[num_zona].getContador();
        zona_total[num_zona].eliminar_carro(pos_car);
        for(int i=pos_car;i<cont-1;i++){
            zona_total[num_zona].correr_carro(i);
        }
        pagar(carro,tiempo);
    }

    
}
