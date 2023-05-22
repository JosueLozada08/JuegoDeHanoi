import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class Interfaz extends JFrame {

    private JPanel Hanoi;
    private JPanel panelDown;
    private JPanel PanelUp;
    private JPanel torre1;
    private JPanel torre3;
    private JPanel torre2;
    private JButton bt1;
    private JButton ct1;
    private JButton at2;
    private JButton ct2;
    private JButton at3;
    private JButton bt3;
    private JComboBox combot;
    private JButton iniciar;
    private JButton reiniciar;
    private JButton resolverButton;
    private JLabel minMovimientos;
    private JLabel numMovimientos;
    private JPanel fieldPanel;
    private JPanel fieldPanel2;
    private JPanel fieldPanel3;
    Torre torre = new Torre();
    private boolean continueFlag = true;
    private int paso = 0;

    public Interfaz() {
        setContentPane(Hanoi);


        torre1.setLayout(new BorderLayout());
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
        torre1.add(fieldPanel, BorderLayout.SOUTH);


        torre2.setLayout(new BorderLayout());
        fieldPanel2 = new JPanel();
        fieldPanel2.setLayout(new BoxLayout(fieldPanel2, BoxLayout.PAGE_AXIS));
        torre2.add(fieldPanel2, BorderLayout.SOUTH);


        torre3.setLayout(new BorderLayout());
        fieldPanel3 = new JPanel();
        fieldPanel3.setLayout(new BoxLayout(fieldPanel3, BoxLayout.PAGE_AXIS));
        torre3.add(fieldPanel3, BorderLayout.SOUTH);

        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicialApivote();
            }
        });
        ct1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicialAdestino();
            }
        });
        at2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pivoteAinicial();
            }
        });
        ct2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pivoteAdestino();
            }
        });
        at3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destinoAinicial();
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destinoApivote();
            }
        });
        resolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolver();
            }
        });
        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });
    }

    private void iniciar(){
        paso = 0;
        torre.setIntentos(0);
        numMovimientos.setText(String.valueOf(torre.getIntentos()));

        fieldPanel.removeAll();
        fieldPanel2.removeAll();
        fieldPanel3.removeAll();

        torre.getTorre1().clear();
        torre.getTorre2().clear();
        torre.getTorre3().clear();

        int discosSeleccionados = Integer.parseInt(combot.getSelectedItem().toString());

        torre.setNumDis(discosSeleccionados);
        torre.setMinIntentos();

        for (int i = 0; i < discosSeleccionados; i++) {
            String hashtags = "";
            JTextField field = new JTextField(10);
            for (int j = 0; j < discosSeleccionados-i; j++) {
                hashtags += "#";
            }
            torre.getTorre1().add(hashtags);
            field.setText(hashtags);
            field.setHorizontalAlignment(SwingConstants.CENTER);
            field.setMaximumSize(field.getPreferredSize());
            fieldPanel.add(field,0);
        }
        fieldPanel.revalidate();
        fieldPanel.repaint();

        minMovimientos.setText(String.valueOf(torre.getMinIntentos()));
    }

    private void reiniciar(){
        torre.setIntentos(0);
        numMovimientos.setText(String.valueOf(torre.getIntentos()));

        fieldPanel.removeAll();
        fieldPanel2.removeAll();
        fieldPanel3.removeAll();

        torre.getTorre1().clear();
        torre.getTorre2().clear();
        torre.getTorre3().clear();

        fieldPanel.revalidate();
        fieldPanel.repaint();
    }
    private void inicialApivote(){
        if (fieldPanel.getComponentCount() > 0) {
            try {
                // Si la torre pivote está vacía, no haremos la comparación.
                if (!torre.getTorre2().empty() && torre.getTorre2().peek().compareTo(torre.getTorre1().peek()) <= 0) {
                    return; // Si la comparación es inválida, termina el método aquí.
                }
            } catch (EmptyStackException e) {

            }
            Component component = fieldPanel.getComponent(0);
            if (component instanceof JTextField) {
                torre.getTorre2().add(torre.getTorre1().pop());

                JTextField field = (JTextField) component;

                JPanel pivotFieldPanel = (JPanel) torre2.getComponent(0);

                // Mueve el JTextField de fieldPanel a pivotFieldPanel
                fieldPanel.remove(field);
                pivotFieldPanel.add(field, 0);

                fieldPanel.revalidate();
                fieldPanel.repaint();

                pivotFieldPanel.revalidate();
                pivotFieldPanel.repaint();

                torre.setIntentos(torre.getIntentos()+1);
                numMovimientos.setText(String.valueOf(torre.getIntentos()));

                if (juegoCompletado()) {

                    JOptionPane.showMessageDialog(null, "Juego completado.");
                }
            }
        }
    }
    private void inicialAdestino(){
        if (fieldPanel.getComponentCount() > 0) {
            try {
                // Si la torre pivote está vacía, no haremos la comparación.
                if (!torre.getTorre3().empty() && torre.getTorre3().peek().compareTo(torre.getTorre1().peek()) <= 0) {
                    return; // Si la comparación es inválida, termina el método aquí.
                }
            } catch (EmptyStackException e) {
                // No haremos nada aquí, porque si la torre pivote está vacía, queremos continuar con el movimiento.
            }
            Component component = fieldPanel.getComponent(0);
            if (component instanceof JTextField) {
                torre.getTorre3().add(torre.getTorre1().pop());

                JTextField field = (JTextField) component;

                JPanel pivotFieldPanel = (JPanel) torre3.getComponent(0);

                // Mueve el JTextField de fieldPanel a pivotFieldPanel
                fieldPanel.remove(field);
                pivotFieldPanel.add(field, 0);

                fieldPanel.revalidate();
                fieldPanel.repaint();

                pivotFieldPanel.revalidate();
                pivotFieldPanel.repaint();

                torre.setIntentos(torre.getIntentos()+1);
                numMovimientos.setText(String.valueOf(torre.getIntentos()));

                if (juegoCompletado()) {
                    // Aquí puedes hacer lo que necesites cuando el juego se complete.
                    JOptionPane.showMessageDialog(null, "Juego completado.");
                }
            }
        }
    }
    private void pivoteAinicial(){
        if (fieldPanel2.getComponentCount() > 0) {
            try {
                // Si la torre pivote está vacía, no haremos la comparación.
                if (!torre.getTorre1().empty() && torre.getTorre1().peek().compareTo(torre.getTorre2().peek()) <= 0) {
                    return; // Si la comparación es inválida, termina el método aquí.
                }
            } catch (EmptyStackException e) {
                // No haremos nada aquí, porque si la torre pivote está vacía, queremos continuar con el movimiento.
            }
            Component component = fieldPanel2.getComponent(0);
            if (component instanceof JTextField) {
                torre.getTorre1().add(torre.getTorre2().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) torre1.getComponent(0);

                // Mueve el JTextField de fieldPanel2 a initialFieldPanel
                fieldPanel2.remove(field);
                initialFieldPanel.add(field, 0);

                fieldPanel2.revalidate();
                fieldPanel2.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                torre.setIntentos(torre.getIntentos()+1);
                numMovimientos.setText(String.valueOf(torre.getIntentos()));

                if (juegoCompletado()) {
                    // Aquí puedes hacer lo que necesites cuando el juego se complete.
                    JOptionPane.showMessageDialog(null, "Juego completado.");
                }
            }
        }
    }
    private void pivoteAdestino(){
        if (fieldPanel2.getComponentCount() > 0) {
            try {
                // Si la torre pivote está vacía, no haremos la comparación.
                if (!torre.getTorre3().empty() && torre.getTorre3().peek().compareTo(torre.getTorre2().peek()) <= 0) {
                    return; // Si la comparación es inválida, termina el método aquí.
                }
            } catch (EmptyStackException e) {
                // No haremos nada aquí, porque si la torre pivote está vacía, queremos continuar con el movimiento.
            }
            Component component = fieldPanel2.getComponent(0);
            if (component instanceof JTextField) {
                torre.getTorre3().add(torre.getTorre2().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) torre3.getComponent(0);

                // Mueve el JTextField de fieldPanel2 a initialFieldPanel
                fieldPanel2.remove(field);
                initialFieldPanel.add(field, 0);

                fieldPanel2.revalidate();
                fieldPanel2.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                torre.setIntentos(torre.getIntentos()+1);
                numMovimientos.setText(String.valueOf(torre.getIntentos()));

                if (juegoCompletado()) {

                    JOptionPane.showMessageDialog(null, "Juego completado.");
                }
            }
        }
    }
    private void destinoAinicial(){
        if (fieldPanel3.getComponentCount() > 0) {
            try {

                if (!torre.getTorre1().empty() && torre.getTorre1().peek().compareTo(torre.getTorre3().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {

            }
            Component component = fieldPanel3.getComponent(0);
            if (component instanceof JTextField) {
                torre.getTorre1().add(torre.getTorre3().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) torre1.getComponent(0);


                fieldPanel3.remove(field);
                initialFieldPanel.add(field, 0);

                fieldPanel3.revalidate();
                fieldPanel3.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                torre.setIntentos(torre.getIntentos()+1);
                numMovimientos.setText(String.valueOf(torre.getIntentos()));

                if (juegoCompletado()) {

                    JOptionPane.showMessageDialog(null, "Juego completado.");
                }
            }
        }
    }

    private void destinoApivote(){
        if (fieldPanel3.getComponentCount() > 0) {
            try {

                if (!torre.getTorre2().empty() && torre.getTorre2().peek().compareTo(torre.getTorre3().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {

            }
            Component component = fieldPanel3.getComponent(0);
            if (component instanceof JTextField) {
                torre.getTorre2().add(torre.getTorre3().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) torre2.getComponent(0);

                // Mueve el JTextField de fieldPanel2 a initialFieldPanel
                fieldPanel3.remove(field);
                initialFieldPanel.add(field, 0);

                fieldPanel3.revalidate();
                fieldPanel3.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                torre.setIntentos(torre.getIntentos()+1);
                numMovimientos.setText(String.valueOf(torre.getIntentos()));

                if (juegoCompletado()) {
                    // Aquí puedes hacer lo que necesites cuando el juego se complete.
                    JOptionPane.showMessageDialog(null, "Juego completado.");
                }
            }
        }
    }
    private void resolver(){
        paso = 0;
        solveHanoi(torre.getNumDis(), "o", "a", "d");
    }

    public void moveDisk(String  origin, String  destination) {
        if(origin.equals("o") && destination.equals("a")){
            inicialApivote();
        }else if(origin.equals("o") && destination.equals("d")){
            inicialAdestino();
        }else if(origin.equals("a") && destination.equals("o")){
            pivoteAinicial();
        }else if(origin.equals("a") && destination.equals("d")){
            pivoteAdestino();
        }else if(origin.equals("d") && destination.equals("o")){
            destinoAinicial();
        }else{
            destinoApivote();
        }
    }

    public boolean askForContinue() {
        if(!juegoCompletado()){
            paso+=1;
            int opcion = JOptionPane.showConfirmDialog(null, "PASO #" + paso + "\n¿Desea continuar?", "Pregunta", JOptionPane.YES_NO_OPTION);
            return opcion == JOptionPane.YES_OPTION;
        }
        return false;
    }

    public void solveHanoi(int n, String origen, String auxiliar, String destino) {
        if(!juegoCompletado()){
            if (n == 1) {
                moveDisk(origen, destino);
                continueFlag = askForContinue();
            } else {
                solveHanoi(n - 1, origen, destino, auxiliar);
                if (continueFlag) {
                    moveDisk(origen, destino);
                    continueFlag = askForContinue();
                }
                if (continueFlag) {
                    solveHanoi(n - 1, auxiliar, origen, destino);
                }
            }
        }
    }

    private boolean juegoCompletado() {
        return torre.getTorre3().size() == torre.getNumDis() && torre.getTorre1().isEmpty() && torre.getTorre2().isEmpty();
    }

}
