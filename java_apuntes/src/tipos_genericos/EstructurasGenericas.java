package tipos_genericos;
/**
 * ESTRUCTURAS DE DATOS GENÉRICAS
 * ================================
 * Implementación desde cero de estructuras clásicas usando genéricos.
 * El objetivo es entender cómo funcionan internamente las colecciones de Java.
 *
 * Estructuras implementadas:
 *   - Lista enlazada simple (LinkedList)
 *   - Pila (Stack)
 *   - Cola (Queue)
 *   - Cola de prioridad (PriorityQueue)
 *   - Árbol binario de búsqueda (BST)
 */
public class EstructurasGenericas {

    public static void main(String[] args) {

        // ================================================================
        // A. LISTA ENLAZADA GENÉRICA
        // ================================================================
        System.out.println("========== A. Lista Enlazada <T> ==========");

        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregarAlFinal(1);
        lista.agregarAlFinal(2);
        lista.agregarAlFinal(3);
        lista.agregarAlInicio(0);
        lista.agregarEnPosicion(1, 99);

        System.out.println("Lista    : " + lista);
        System.out.println("Tamaño   : " + lista.tamaño());
        System.out.println("Primero  : " + lista.obtener(0));
        System.out.println("Último   : " + lista.obtener(lista.tamaño() - 1));
        System.out.println("Contiene 99: " + lista.contiene(99));
        System.out.println("Índice de 99: " + lista.indiceDe(99));

        lista.eliminar(99);
        System.out.println("Sin 99   : " + lista);

        lista.eliminarEnPosicion(0);
        System.out.println("Sin pos 0: " + lista);

        // Con String
        ListaEnlazada<String> palabras = new ListaEnlazada<>();
        palabras.agregarAlFinal("Java");
        palabras.agregarAlFinal("Python");
        palabras.agregarAlFinal("Go");
        System.out.println("Strings  : " + palabras);

        // ================================================================
        // B. PILA GENÉRICA (LIFO — Last In First Out)
        // ================================================================
        System.out.println("\n========== B. Pila <T> (LIFO) ==========");

        Pila<String> pila = new Pila<>();
        pila.push("primero");
        pila.push("segundo");
        pila.push("tercero");

        System.out.println("Pila     : " + pila);
        System.out.println("peek()   : " + pila.peek());    // ver sin quitar
        System.out.println("pop()    : " + pila.pop());     // quitar
        System.out.println("pop()    : " + pila.pop());
        System.out.println("Pila     : " + pila);
        System.out.println("Vacía?   : " + pila.estaVacia());

        // Caso de uso: deshacer operaciones
        System.out.println("\n--- Caso de uso: historial de operaciones ---");
        Pila<String> historial = new Pila<>();
        historial.push("Abrir archivo");
        historial.push("Escribir 'Hola'");
        historial.push("Negrita");
        historial.push("Subrayado");

        System.out.println("Deshaciendo:");
        while (!historial.estaVacia()) {
            System.out.println("  Deshacer: " + historial.pop());
        }

        // Caso de uso: verificar paréntesis balanceados
        String[] expresiones = {"(()())", "(()", "({[]})", "([)]"};
        for (String exp : expresiones) {
            System.out.println(exp + " → balanceada: " + balanceada(exp));
        }

        // ================================================================
        // C. COLA GENÉRICA (FIFO — First In First Out)
        // ================================================================
        System.out.println("\n========== C. Cola <T> (FIFO) ==========");

        Cola<String> cola = new Cola<>();
        cola.encolar("primero");
        cola.encolar("segundo");
        cola.encolar("tercero");

        System.out.println("Cola     : " + cola);
        System.out.println("frente() : " + cola.frente());
        System.out.println("desencolar: " + cola.desencolar());
        System.out.println("desencolar: " + cola.desencolar());
        System.out.println("Cola     : " + cola);

        // Caso de uso: sistema de tickets
        System.out.println("\n--- Sistema de atención al cliente ---");
        Cola<String> tickets = new Cola<>();
        tickets.encolar("Cliente A");
        tickets.encolar("Cliente B");
        tickets.encolar("Cliente C");

        while (!tickets.estaVacia()) {
            System.out.println("Atendiendo: " + tickets.desencolar());
        }

        // ================================================================
        // D. COLA DE PRIORIDAD GENÉRICA
        // ================================================================
        System.out.println("\n========== D. Cola de Prioridad <T> ==========");

        ColaPrioridad<Integer> cp = new ColaPrioridad<>();
        cp.insertar(5);
        cp.insertar(1);
        cp.insertar(8);
        cp.insertar(3);
        cp.insertar(7);

        System.out.print("Extracción ordenada: ");
        while (!cp.estaVacia()) {
            System.out.print(cp.extraerMinimo() + " ");
        }
        System.out.println();

        // Con String (orden lexicográfico)
        ColaPrioridad<String> cpStr = new ColaPrioridad<>();
        cpStr.insertar("banana");
        cpStr.insertar("apple");
        cpStr.insertar("cherry");
        cpStr.insertar("date");

        System.out.print("Strings ordenados: ");
        while (!cpStr.estaVacia()) {
            System.out.print(cpStr.extraerMinimo() + " ");
        }
        System.out.println();

        // ================================================================
        // E. ÁRBOL BINARIO DE BÚSQUEDA GENÉRICO
        // ================================================================
        System.out.println("\n========== E. BST <T extends Comparable<T>> ==========");

        ArbolBST<Integer> arbol = new ArbolBST<>();
        int[] valores = {5, 3, 7, 1, 4, 6, 8};
        for (int v : valores) arbol.insertar(v);

        System.out.print("InOrder  (asc): "); arbol.inOrder();     System.out.println();
        System.out.print("PreOrder      : "); arbol.preOrder();    System.out.println();
        System.out.print("PostOrder     : "); arbol.postOrder();   System.out.println();

        System.out.println("Contiene 4: " + arbol.contiene(4));
        System.out.println("Contiene 9: " + arbol.contiene(9));
        System.out.println("Mínimo    : " + arbol.minimo());
        System.out.println("Máximo    : " + arbol.maximo());
        System.out.println("Altura    : " + arbol.altura());

        arbol.eliminar(3);
        System.out.print("Sin el 3  : "); arbol.inOrder(); System.out.println();

        // Con String
        ArbolBST<String> arbolStr = new ArbolBST<>();
        String[] palabrasArr = {"manzana", "banana", "cereza", "aguacate", "dátil"};
        for (String p : palabrasArr) arbolStr.insertar(p);
        System.out.print("Frutas BST: "); arbolStr.inOrder(); System.out.println();
    }

    // Verificar paréntesis balanceados con Pila
    static boolean balanceada(String expr) {
        Pila<Character> pila = new Pila<>();
        for (char c : expr.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pila.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pila.estaVacia()) return false;
                char tope = pila.pop();
                if ((c == ')' && tope != '(') ||
                    (c == '}' && tope != '{') ||
                    (c == ']' && tope != '[')) return false;
            }
        }
        return pila.estaVacia();
    }
}

// ====================================================================
// LISTA ENLAZADA GENÉRICA
// ====================================================================
class ListaEnlazada<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> cabeza;
    private int     tamaño;

    public void agregarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) { cabeza = nuevo; }
        else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    public void agregarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamaño++;
    }

    public void agregarEnPosicion(int pos, T dato) {
        if (pos == 0) { agregarAlInicio(dato); return; }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < pos - 1 && actual != null; i++) actual = actual.siguiente;
        if (actual == null) throw new IndexOutOfBoundsException("Posición inválida");
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        tamaño++;
    }

    public T obtener(int pos) {
        Nodo<T> actual = cabeza;
        for (int i = 0; i < pos; i++) actual = actual.siguiente;
        return actual.dato;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;
        if (java.util.Objects.equals(cabeza.dato, dato)) { cabeza = cabeza.siguiente; tamaño--; return true; }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            if (java.util.Objects.equals(actual.siguiente.dato, dato)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void eliminarEnPosicion(int pos) {
        if (pos == 0) { cabeza = cabeza.siguiente; tamaño--; return; }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < pos - 1; i++) actual = actual.siguiente;
        actual.siguiente = actual.siguiente.siguiente;
        tamaño--;
    }

    public boolean contiene(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (java.util.Objects.equals(actual.dato, dato)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public int indiceDe(T dato) {
        Nodo<T> actual = cabeza;
        int idx = 0;
        while (actual != null) {
            if (java.util.Objects.equals(actual.dato, dato)) return idx;
            actual = actual.siguiente;
            idx++;
        }
        return -1;
    }

    public int     tamaño()   { return tamaño; }
    public boolean estaVacia(){ return tamaño == 0; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) sb.append(" → ");
            actual = actual.siguiente;
        }
        return sb.append("]").toString();
    }
}

// ====================================================================
// PILA GENÉRICA (usa ListaEnlazada)
// ====================================================================
class Pila<T> {
    private java.util.ArrayList<T> elementos = new java.util.ArrayList<>();

    public void push(T e)   { elementos.add(e); }
    public T    pop()       {
        if (estaVacia()) throw new java.util.EmptyStackException();
        return elementos.remove(elementos.size() - 1);
    }
    public T    peek()      {
        if (estaVacia()) throw new java.util.EmptyStackException();
        return elementos.get(elementos.size() - 1);
    }
    public boolean estaVacia() { return elementos.isEmpty(); }
    public int     tamaño()    { return elementos.size(); }

    @Override
    public String toString() {
        return "Pila" + elementos + " ← tope";
    }
}

// ====================================================================
// COLA GENÉRICA
// ====================================================================
class Cola<T> {
    private java.util.LinkedList<T> elementos = new java.util.LinkedList<>();

    public void encolar(T e)   { elementos.addLast(e); }
    public T    desencolar()   {
        if (estaVacia()) throw new java.util.NoSuchElementException("Cola vacía");
        return elementos.removeFirst();
    }
    public T    frente()       {
        if (estaVacia()) throw new java.util.NoSuchElementException("Cola vacía");
        return elementos.getFirst();
    }
    public boolean estaVacia() { return elementos.isEmpty(); }
    public int     tamaño()    { return elementos.size(); }

    @Override
    public String toString() { return "frente→" + elementos + "←atrás"; }
}

// ====================================================================
// COLA DE PRIORIDAD GENÉRICA (min-heap simple con lista)
// ====================================================================
class ColaPrioridad<T extends Comparable<T>> {
    private java.util.ArrayList<T> heap = new java.util.ArrayList<>();

    public void insertar(T e) {
        heap.add(e);
        // Bubble up
        int i = heap.size() - 1;
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap.get(i).compareTo(heap.get(padre)) < 0) {
                T tmp = heap.get(i); heap.set(i, heap.get(padre)); heap.set(padre, tmp);
                i = padre;
            } else break;
        }
    }

    public T extraerMinimo() {
        if (heap.isEmpty()) throw new java.util.NoSuchElementException();
        T min = heap.get(0);
        T ultimo = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            // Sift down
            int i = 0, n = heap.size();
            while (true) {
                int menor = i, l = 2*i+1, r = 2*i+2;
                if (l < n && heap.get(l).compareTo(heap.get(menor)) < 0) menor = l;
                if (r < n && heap.get(r).compareTo(heap.get(menor)) < 0) menor = r;
                if (menor == i) break;
                T tmp = heap.get(i); heap.set(i, heap.get(menor)); heap.set(menor, tmp);
                i = menor;
            }
        }
        return min;
    }

    public boolean estaVacia() { return heap.isEmpty(); }
}

// ====================================================================
// ÁRBOL BINARIO DE BÚSQUEDA GENÉRICO
// ====================================================================
class ArbolBST<T extends Comparable<T>> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> izq, der;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> raiz;

    public void insertar(T dato) { raiz = insertarRec(raiz, dato); }
    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return new Nodo<>(dato);
        int cmp = dato.compareTo(nodo.dato);
        if      (cmp < 0) nodo.izq = insertarRec(nodo.izq, dato);
        else if (cmp > 0) nodo.der = insertarRec(nodo.der, dato);
        return nodo;
    }

    public boolean contiene(T dato) { return contiene(raiz, dato); }
    private boolean contiene(Nodo<T> n, T d) {
        if (n == null) return false;
        int cmp = d.compareTo(n.dato);
        if (cmp < 0) return contiene(n.izq, d);
        if (cmp > 0) return contiene(n.der, d);
        return true;
    }

    public T minimo() {
        if (raiz == null) throw new java.util.NoSuchElementException();
        Nodo<T> n = raiz;
        while (n.izq != null) n = n.izq;
        return n.dato;
    }

    public T maximo() {
        if (raiz == null) throw new java.util.NoSuchElementException();
        Nodo<T> n = raiz;
        while (n.der != null) n = n.der;
        return n.dato;
    }

    public int altura() { return altura(raiz); }
    private int altura(Nodo<T> n) {
        if (n == null) return 0;
        return 1 + Math.max(altura(n.izq), altura(n.der));
    }

    public void eliminar(T dato) { raiz = eliminarRec(raiz, dato); }
    private Nodo<T> eliminarRec(Nodo<T> n, T d) {
        if (n == null) return null;
        int cmp = d.compareTo(n.dato);
        if      (cmp < 0) n.izq = eliminarRec(n.izq, d);
        else if (cmp > 0) n.der = eliminarRec(n.der, d);
        else {
            if (n.izq == null) return n.der;
            if (n.der == null) return n.izq;
            // Sucesor inorden (mínimo del subárbol derecho)
            Nodo<T> sucesor = n.der;
            while (sucesor.izq != null) sucesor = sucesor.izq;
            n.dato = sucesor.dato;
            n.der  = eliminarRec(n.der, sucesor.dato);
        }
        return n;
    }

    public void inOrder()  { inOrder(raiz); }
    private void inOrder(Nodo<T> n)  { if(n!=null){ inOrder(n.izq); System.out.print(n.dato+" "); inOrder(n.der); } }

    public void preOrder() { preOrder(raiz); }
    private void preOrder(Nodo<T> n) { if(n!=null){ System.out.print(n.dato+" "); preOrder(n.izq); preOrder(n.der); } }

    public void postOrder(){ postOrder(raiz); }
    private void postOrder(Nodo<T> n){ if(n!=null){ postOrder(n.izq); postOrder(n.der); System.out.print(n.dato+" "); } }
}
