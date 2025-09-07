import java.util.Random;

public class Controlador{
    private Candidato candidatos[];
    private Long comparaciones=(long) 0;
    private Long cambios=(long)0;
    private String nombres[]= {"Luis","Andres","Daniel","Humberto","Rafael","Augusto"};
    private String apellidos[]= {"Perez","Rodriguez","Vargas","Cordoba","Acosta","Uribe"};
    private Random r;

    public Controlador(int n, int m){
        r= new Random();
        candidatos = new Candidato[n];
        for(int i=0; i<n; i++){
            candidatos[i] = new Candidato(i,m,nombres[r.nextInt(nombres.length)]+" "+apellidos[r.nextInt(apellidos.length)]);
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

public Candidato[] getCandidatos(){
    return this.candidatos;
}

/**Determina el estado inicial de los datos, utilizando merge para organizar en caso de no dejar la opcion por defecto */
public void estado(Candidato[] arr, int atributo, int estado){
    switch(estado){
        case 1:
            mergeSort(arr, atributo);
            invertir(arr);
            break;
        case 2:
            mergeSort(arr, atributo);
            desordenar(arr);
        default:
            break;
    }
}

private void invertir(Candidato[] arr) {
    int i = 0, j = arr.length - 1;
    while (i < j) {
        swap(arr, i, j);
        i++;
        j--;
    }
}

private void desordenar(Candidato[] arr) {
    Random rand = new Random();
    for (int k = 0; k < arr.length / 10; k++) { // desordena ~10% de elementos
        int i = rand.nextInt(arr.length);
        int j = rand.nextInt(arr.length);
        swap(arr, i, j);
    }
}

// Método para comparar según atributo
private boolean mayorQue(Candidato a, Candidato b, int atributo) {
    comparaciones++;
    switch (atributo) {
        case 1: return a.getTotalMarchas() > b.getTotalMarchas();
        case 2: return a.getTotalBloqueos() > b.getTotalBloqueos();
        case 3: return a.getTotalPoliticos() > b.getTotalPoliticos();
        case 4: return a.getTotalPoliticos() > b.getTotalPrebendas();
        case 5: return a.getTotalSobornos() > b.getTotalSobornos();
        default: return false;
    }
}

// Intercambio
private void swap(Candidato[] arr, int i, int j) {
    cambios++;
    Candidato temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

// ================== BURBUJA ==================
public void burbuja(Candidato[] arr, int atributo) {
    comparaciones=(long)0;
    cambios=(long)0;
    long inicio = System.nanoTime();
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (mayorQue(arr[j], arr[j+1], atributo)) {
                swap(arr, j, j+1);
            }
        }
    }
    long fin = System.nanoTime();
    long tiempo = fin-inicio;
    System.err.println("Tiempo total de ejecución: "+tiempo+" nanosegundos \n");
    System.err.println("Comparaciones: "+comparaciones+"\n");
    System.out.println("Intercambios: "+cambios+"\n");
}

// ================== INSERCIÓN ==================
public void insercion(Candidato[] arr, int atributo) {
    comparaciones=(long)0;
    cambios=(long)0;
    long inicio = System.nanoTime();
    int n = arr.length;
    for (int i = 1; i < n; i++) {
        Candidato key = arr[i];
        int j = i - 1;
        while (j >= 0 && mayorQue(arr[j], key, atributo)) {
            arr[j+1] = arr[j];
            j--;
            cambios++;
        }
        arr[j+1] = key;
    }
    long fin = System.nanoTime();
    long tiempo = fin-inicio;
    System.err.println("Tiempo total de ejecución: "+tiempo+" nanosegundos \n");
    System.err.println("Comparaciones: "+comparaciones+"\n");
    System.out.println("Intercambios: "+cambios+"\n");
}

// ================== SELECCIÓN ==================
public void seleccion(Candidato[] arr, int atributo) {
    comparaciones=(long)0;
    cambios=(long)0;
    long inicio = System.nanoTime();
    int n = arr.length;
    for (int i = 0; i < n-1; i++) {
        int minIdx = i;
        for (int j = i+1; j < n; j++) {
            if (mayorQue(arr[minIdx], arr[j], atributo)) {
                minIdx = j;
            }
        }
        swap(arr, i, minIdx);
    }
    long fin = System.nanoTime();
    long tiempo = fin-inicio;
    System.err.println("Tiempo total de ejecución: "+tiempo+" nanosegundos \n");
    System.err.println("Comparaciones: "+comparaciones+"\n");
    System.out.println("Intercambios: "+cambios+"\n");
}

// ================== QUICKSORT ==================
public void quickSort(Candidato[] arr, int atributo) {
    comparaciones=(long)0;
    cambios=(long)0;
    long inicio = System.nanoTime();
    quickSortRec(arr, 0, arr.length-1, atributo);
    long fin = System.nanoTime();
    long tiempo = fin-inicio;
    System.err.println("Tiempo total de ejecución: "+tiempo+" nanosegundos \n");
    System.err.println("Comparaciones: "+comparaciones+"\n");
    System.out.println("Intercambios: "+cambios+"\n");
}

private void quickSortRec(Candidato[] arr, int low, int high, int atributo) {
    if (low < high) {
        int pi = partition(arr, low, high, atributo);
        quickSortRec(arr, low, pi-1, atributo);
        quickSortRec(arr, pi+1, high, atributo);
    }
}

private int partition(Candidato[] arr, int low, int high, int atributo) {
    Candidato pivot = arr[high];
    int i = low-1;
    for (int j = low; j < high; j++) {
        if (mayorQue(pivot, arr[j], atributo)) {
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i+1, high);
    return i+1;
}

// ================== MERGESORT ==================
public void mergeSort(Candidato[] arr, int atributo) {
    comparaciones=(long)0;
    cambios=(long)0;
    long inicio = System.nanoTime();
    mergeSortRec(arr, 0, arr.length-1, atributo);
    long fin = System.nanoTime();
    long tiempo = fin-inicio;
    System.err.println("Tiempo total de ejecución: "+tiempo+" nanosegundos \n");
    System.err.println("Comparaciones: "+comparaciones+"\n");
    System.out.println("Intercambios: "+cambios+"\n");
}

private void mergeSortRec(Candidato[] arr, int l, int r, int atributo) {
    if (l < r) {
        int m = (l+r)/2;
        mergeSortRec(arr, l, m, atributo);
        mergeSortRec(arr, m+1, r, atributo);
        merge(arr, l, m, r, atributo);
    }
}

private void merge(Candidato[] arr, int l, int m, int r, int atributo) {
    int n1 = m - l + 1;
    int n2 = r - m;

    Candidato[] L = new Candidato[n1];
    Candidato[] R = new Candidato[n2];

    for (int i = 0; i < n1; i++)
        L[i] = arr[l+i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m+1+j];

    int i = 0, j = 0, k = l;
    while (i < n1 && j < n2) {
        if (!mayorQue(L[i], R[j], atributo)) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
            cambios++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++; k++;
    }
    while (j < n2) {
        arr[k] = R[j];
        j++; k++;
    }
}
}
