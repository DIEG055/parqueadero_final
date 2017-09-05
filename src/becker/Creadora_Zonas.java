package becker;

public class Creadora_Zonas {
    private Carro[] zona;
    private int contador=0;
    
//Metodos
    public Creadora_Zonas() {
        this.zona = new Carro[5];
    }
    
    public int getContador() {
        return contador;
    }
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public Carro mover_carro(int posicion_carro){
        return zona[posicion_carro];
    }
    
    public void correr_carro(int posicion_carro){
    zona[posicion_carro]=zona[posicion_carro+1];
    }
    
    public void agregar_carro(Carro carro){
        if(contador<5){
            zona[contador]=carro;
            contador ++;
        }
    }
    
    public String placa_i(int i){
        return zona[i].getPlaca();
    }
    
    public void eliminar_carro(int k){
        zona[k]=null;
        contador --;
    }
    
    public int posicion_carro_guardado(Carro carro){
        //el 6 es solo por inicializar variable
        int h=6;
        for(int i=0;i<contador;i++){
            if(zona[i].getPlaca().equals(carro.getPlaca())){
                h=i;
                break;
            }
        }
        return h;
    }
    
    public boolean parqueado_en_esta_zona(Carro carro){
        boolean ac =false;
        for(int i=0;i<contador;i++){
            if(zona[i].getPlaca().equals(carro.getPlaca())){
                ac=true;
                break;
            }
        }
        return ac;
    }
    
    public boolean parqueado_en_esta_zona(String placa){
        boolean ac =false;
        for(int i=0;i<contador;i++){
            if(zona[i].getPlaca().equals(placa)){
                ac=true;
                break;
            }
        }
        return ac;
    }
        public int carro_parqueado(String placa){
        int k=0;
            for(int i=0;i<contador;i++){
            if(zona[i].getPlaca().equals(placa)){
                 k=i;
                break;
            }
        }
        return k;
    }
}