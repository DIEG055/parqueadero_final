package becker;



public class Zona_Parqueo {
    private Creadora_Zonas[] zona_total;
    private int indice;
    private  Carro[] zona_temporal;
    //metodos
    public Zona_Parqueo(){    
        this.zona_total= new Creadora_Zonas[3];
        for(int i=0;i<3;i++){
        zona_total[i]= new Creadora_Zonas();
        }
        this.zona_temporal= new Carro[4];
    }

    //hago un arreglo con las 3 zonas,guardo como menor al inico la zona 1, luego
    //evaluo con las otras dos, si alguna tiene mas espacio para guardar carros, pasa a ser la temporal
    //al final de vuelvo la la zona mas desocupada
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
    
    /**
     *
     * @param carro
     * @return
     */
    public boolean agregar_carro_a_zona(Carro carro){
        int k=mejor_zona();
         //el contador comienza en 0, por eso es hasta 4
        if(zona_total[k].getContador()==5){
           
            return false;
        }else{
        zona_total[k].agregar_carro(carro);
        return true;
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
        int j=0;   
        for(int i=0;i<3;i++){
                if(zona_total[i].parqueado_en_esta_zona(carro)){
                    j=i;
                    break;
                }
                
            } 
    return j;
    }
    
    public boolean sacar_carro(Carro carro,int numero_zona){
        int k=zona_total[numero_zona].posicion_carro_guardado(carro);
        if(k>4){
        // no se encontro la placa
            return false;
        }else{
            //correr los carros a la zona temporal y devolverlos"esto es hecho para karel"
       
            if(k==zona_total[numero_zona].getContador()){
            // si esta de ultimas, no tiene carros que impidan salir
               zona_total[numero_zona].eliminar_carro(k);
            }else{
              // variable de cuantos carros se van a mover hasta que pueda salir el carro
              int tam_temporal=zona_total[numero_zona].getContador()-k;
                //mueve los carros de la zona i a la zona temporal
                for(int i=0;i<tam_temporal;i++){
                    zona_temporal[i]=zona_total[numero_zona].mover_carro(k-i);
                    // se supone que si se mueve el carro el espacio en la zona donde estaba queda vacio
                    zona_total[numero_zona].eliminar_carro(k-i);
                }//ya no hay carros que eviten sacar el carro
                // si esta de ultimas, no tiene carros que impidan salir
                //sigue siendo la posicion k,ya que se movieron los carros que encima de el. el carro sigue quieto   
                zona_total[numero_zona].eliminar_carro(k);
                //ahora si se movio
        
                //ahora que el carro salio se debe devolver los que estan en la zona temporal
                for(int i=0;i<tam_temporal;i++){
                    zona_total[numero_zona].agregar_carro(zona_temporal[i]);
                }
            }
    return true;
        }
    }
    
}
