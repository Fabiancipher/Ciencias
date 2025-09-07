import java.util.*;
public class Candidato{
    
    private int id;
    private String nombre;
    private Marcha marchas[];
    private Bloqueo bloqueos[];
    private Prebenda prebendas[];
    private Politico politicos[];
    private ActoCorrupcion sobornos[];
    private long totalMarchas;
    private long totalBloqueos;      
    private long totalPrebendas;
    private long totalPoliticos;
    private long totalSobornos;
    private Random r;
    public String nombreMarchas[]= {"Marcha1","Marcha2","Marcha3","Marcha4","Marcha5"};
    public String nombreBloqueos[]= {"Bloqueo1","Bloqueo2","Bloqueo3","Bloqueo4","Bloqueo5"};
    public String nombrePrebendas[]= {"Prebenda1","Prebenda2","Prebenda3","Prebenda4","Prebenda5"};
    public String nombrePoliticos[]= {"Politico1","Politico2","Politico3","Politico4","Politico5"};
    public String nombreSobornos[]= {"Soborno1","Soborno2","Soborno3","Soborno4","Soborno5"};

    public Candidato(int id, int n, String nombre){
        this.id = id+1;
        this.nombre = nombre;
        marchas = new Marcha[n];
        bloqueos = new Bloqueo[n];
        prebendas = new Prebenda[n];
        politicos = new Politico[n];
        sobornos = new ActoCorrupcion[n];
        r = new Random();
    }

    public void rellenarAtributos(int n){
        for(int i=0; i<n; i++){
            marchas[i] = new Marcha(nombreMarchas[r.nextInt(nombreMarchas.length)],r.nextInt(2001)); //Escoge aleatoriamente de la lista y crea un numero aleatorio
            bloqueos[i] = new Bloqueo(nombreBloqueos[r.nextInt(nombreBloqueos.length)],r.nextInt(17));
            prebendas[i] = new Prebenda(nombrePrebendas[r.nextInt(nombrePoliticos.length)],r.nextInt(4000001));
            politicos[i] = new Politico(nombrePoliticos[r.nextInt(nombrePoliticos.length)],r.nextInt(4000001));
            sobornos[i] = new ActoCorrupcion(nombreSobornos[r.nextInt(nombreSobornos.length)],r.nextInt(4000001));
        }
    }
    /**Suma todos los valores obtenidosx */
    public void obtenerTotales(){
        this.totalMarchas = 0;
        this.totalBloqueos = 0;
        this.totalPrebendas = 0;
        this.totalPoliticos = 0;
        this.totalSobornos = 0;
        for(int i=0; i<marchas.length; i++){
            totalMarchas += marchas[i].getDistancia();
            totalBloqueos += bloqueos[i].getHoras();
            totalPrebendas += prebendas[i].getValorPre();
            totalPoliticos += politicos[i].getValorPoli();
            totalSobornos += sobornos[i].getValorSobo();
        }
    }

    public String getNombre(){
        return this.nombre;
    }

    public long getTotalMarchas(){
        return this.totalMarchas;
    }

    public long getTotalBloqueos(){
        return this.totalBloqueos;
    }

    public long getTotalPrebendas(){
        return this.totalPrebendas;
    }

    public long getTotalPoliticos(){
        return this.totalPoliticos;
    }

    public long getTotalSobornos(){
        return this.totalSobornos;
    }

    /**
     * Regresa una representacion del candidato
     *
     */
    @Override
    public String toString(){
        if(marchas.length<100){
             return "Candidato N°: "+this.id+"\n"+"NOMBRE: "+this.nombre+"\n"+
               "MARCHAS: "+Arrays.toString(this.marchas)+"\n"+
               "BLOQUEOS: "+Arrays.toString(this.bloqueos)+"\n"+
               "PREBENDAS: "+Arrays.toString(this.prebendas)+"\n"+
               "POLITICOS: "+Arrays.toString(this.politicos)+"\n"+
               "ACTOS DE CORRUPCION: "+Arrays.toString(this.sobornos)+"\n"+
               "TOTAL DISTANCIA EN MARCHAS: ["+this.totalMarchas+"]\n"+
               "TOTAL HORAS PERDIDAS EN BLOQUEOS: ["+this.totalBloqueos+"]\n"+
               "TOTAL VALOR EN PREBENDAS: ["+this.totalPrebendas+"]\n"+
               "TOTAL VALOR EN SOBORNOS DE POLITICOS: ["+this.totalPoliticos+"]\n"+
               "TOTAL VALOR EN ACTOS DE CORRUPCION: ["+this.totalSobornos+"]\n";
        }
       else{
               return "Demasiados Eventos, mostrando solo Totales \n"+
               "Candidato N°: "+this.id+"\n"+"NOMBRE: "+this.nombre+"\n"+
               "TOTAL DISTANCIA EN MARCHAS: ["+this.totalMarchas+"]\n"+
               "TOTAL HORAS PERDIDAS EN BLOQUEOS: ["+this.totalBloqueos+"]\n"+
               "TOTAL VALOR EN PREBENDAS: ["+this.totalPrebendas+"]\n"+
               "TOTAL VALOR EN SOBORNOS DE POLITICOS: ["+this.totalPoliticos+"]\n"+
               "TOTAL VALOR EN ACTOS DE CORRUPCION: ["+this.totalSobornos+"]\n";
       }
    }
}