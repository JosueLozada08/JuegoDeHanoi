import java.util.Stack;

public class Torre {

    private Stack<String> torre1;
    private Stack<String> torre2;
    private Stack<String> torre3;
    private int numDis;
    private int intentosMinimos;
    private int intentos;


    public Torre(){
        intentos = 0;
        numDis = 0;
        torre1 = new Stack<>();
        torre2 = new Stack<>();
        torre3 = new Stack<>();
    }
    public int getMinIntentos() {
        return intentosMinimos;
    }

    public void setMinIntentos() {
        this.intentosMinimos = ((int)Math.pow(2, numDis))-1;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Stack<String> getTorre1() {
        return torre1;
    }

    public Stack<String> getTorre2() {
        return torre2;
    }

    public Stack<String> getTorre3() {
        return torre3;
    }

    public void setNumDis(int numDis) {
        this.numDis = numDis;
    }

    public int getNumDis() {
        return numDis;
    }

}
