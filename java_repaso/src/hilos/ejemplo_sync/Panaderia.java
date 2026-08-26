package hilos.ejemplo_sync;

public class Panaderia {
    private String pan;
    private boolean disponible;

    public synchronized void hornear(String masa) throws InterruptedException {
        while (disponible) {
            wait();
        }
        this.pan = masa;
        System.out.println("El panadero esta horneando el pan " + pan);
        this.disponible = true;
        notify();
    }

    public synchronized String consumir() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cliente consume: " + this.pan);
        this.disponible = false;
        notify();
        return pan;

    }
}
