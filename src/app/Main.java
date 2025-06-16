package app;
import domain.*;
import exception.LoginInvalidoException;
import view.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main extends JFrame {
    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}