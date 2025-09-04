
public class Controlador{
    private Candidato candidatos[];

    public Controlador(int n, int m){
        candidatos = new Candidato[n];
        for(int i=0; i<n; i++){
            candidatos[i] = new Candidato(i,m,"Candidato"+i);
            candidatos[i].rellenarAtributos(m);
        }
    }
    public void mostrarCandidatos(int n){
        for(int i=0; i<n; i++){
            System.out.println(candidatos[i].toString());
            System.out.println("--------------------------------------------------");
        }
    }
}