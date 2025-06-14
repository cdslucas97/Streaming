package view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout; // Precisamos importar o FlowLayout

public class TelaLogin extends JFrame {

    public TelaLogin() {
        // Define o titulo do programa
        this.setTitle("MovieFlix");
        // Finaliza o programa ao fechar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Define o tamanho da janela
        this.setSize(300, 200);
        // Não permite redimensionar a janela
        this.setResizable(false);

        // Cria o painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        // Componentes de Label e Texto (sem alteração)
        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField campoUsuario = new JTextField(15);
        JLabel labelSenha = new JLabel("Senha:");
        JTextField campoSenha = new JTextField(15);

        campoUsuario.setMaximumSize(campoUsuario.getPreferredSize());
        campoSenha.setMaximumSize(campoSenha.getPreferredSize());

        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Botões
        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoRegistro = new JButton("Registro");
        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoRegistro);

        // Centraliza o painel de botões dentro do layout vertical
        painelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Força o conteudo na vertical
        painelPrincipal.add(Box.createVerticalGlue());

        // Adiciona os componentes ao painel principal
        painelPrincipal.add(labelUsuario);
        painelPrincipal.add(campoUsuario);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(labelSenha);
        painelPrincipal.add(campoSenha);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // Adiciona o painel de botóes
        painelPrincipal.add(painelBotoes);

        this.add(painelPrincipal);
        this.setLocationRelativeTo(null);
    }
}