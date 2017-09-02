package becker;




public class Creadora_Zonas {
    private Carro[] zona;
    private String nombre_zona;
    private int contador=0;
    
//Metodos

    public Creadora_Zonas() {
        this.zona = new Carro[5];
    }

    public Carro[] getZona() {
        return zona;
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
    
    public void agregar_carro(Carro carro){
    zona[contador]=carro;
    contador ++;
    }
    
        public void eliminar_carro(int k){
    zona[k]=null;
    contador --;
    }
    
    public int posicion_carro_guardado(Carro carro){
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
     

}