
public class Controlador{
    private Candidato candidatos[];

    public Controlador(int n, int m){
        candidatos = new Candidato[n];
        for(int i=0; i<n; i++){
            candidatos[i] = new Candidato(i,m,"Candidato"+i);
            candidatos[i].rellenarAtributos(m);
            candidatos[i].obtenerTotales();
        }
    }
    public void mostrarCandidatos(int n){
        for(int i=0; i<n; i++){
            System.out.println(candidatos[i].toString());
            System.out.println("--------------------------------------------------");
        }
    }
    public void burbuja(){
        Candidato aux;
        for(int i=0; i<candidatos.length-1; i++){
            for(int j=0; j<candidatos.length-i-1; j++){
                if(candidatos[j].getTotalSobornos() < candidatos[j+1].getTotalSobornos()){
                    aux = candidatos[j];
                    candidatos[j] = candidatos[j+1];
                    candidatos[j+1] = aux;
                }
            }
        }
    }
    public void seleccion(){
        Candidato aux;
        int pos;
        for(int i=0; i<candidatos.length-1; i++){
            pos = i;
            for(int j=i+1; j<candidatos.length; j++){
                if(candidatos[j].getTotalSobornos() > candidatos[pos].getTotalSobornos()){
                    pos = j;
                }
            }
            if(pos != i){
                aux = candidatos[i];
                candidatos[i] = candidatos[pos];
                candidatos[pos] = aux;
            }
        }
    }
    public void insercion(){
        Candidato aux;
        int j;
        for(int i=1; i<candidatos.length; i++){
            aux = candidatos[i];
            j = i-1;
            while(j>=0 && candidatos[j].getTotalSobornos() < aux.getTotalSobornos()){
                candidatos[j+1] = candidatos[j];
                j--;
            }
            candidatos[j+1] = aux;
        }
    }
    public void quicksort(int izq, int der){
        Candidato pivote = candidatos[izq];
        int i = izq;
        int j = der;
        Candidato aux;
        while(i<j){
            while(candidatos[i].getTotalSobornos() >= pivote.getTotalSobornos() && i<j) i++;
            while(candidatos[j].getTotalSobornos() < pivote.getTotalSobornos()) j--;
            if(i<j){
                aux = candidatos[i];
                candidatos[i] = candidatos[j];
                candidatos[j] = aux;
            }
        }
        candidatos[izq] = candidatos[j];
        candidatos[j] = pivote;
        if(izq<j-1) quicksort(izq,j-1);
        if(j+1<der) quicksort(j+1,der);
    }
}
