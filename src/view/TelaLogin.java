package view;

import data.UsuarioData;
import domain.Administrador;
import domain.Cliente;
import domain.Usuario;
import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        // Titulo
        setTitle("MovieFlix");
        // Operação ao fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Tamanho da janela
        setSize(300, 200);
        // Bloqueia redimensionamento
        setResizable(false);
        // Inicia o programa no meio da tela (
        setLocationRelativeTo(null);


        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JLabel labelEmail = new JLabel("Email:");
        JTextField campoEmail = new JTextField(15);
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField(15);

        campoEmail.setMaximumSize(campoEmail.getPreferredSize());
        campoSenha.setMaximumSize(campoSenha.getPreferredSize());
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoRegistro = new JButton("Registro");
        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoRegistro);
        painelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona componentes ao painel principal
        painelPrincipal.add(Box.createVerticalGlue());
        painelPrincipal.add(labelEmail);
        painelPrincipal.add(campoEmail);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(labelSenha);
        painelPrincipal.add(campoSenha);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPrincipal.add(painelBotoes);
        painelPrincipal.add(Box.createVerticalGlue());

        // Listener botão ENTRAR
        botaoEntrar.addActionListener(e -> {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email e senha devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                List<Usuario> usuarios = UsuarioData.listarTodos();
                Usuario usuarioEncontrado = null;

                // Percorrea lista em busca do usuario compativel
                for (Usuario user : usuarios) {
                    if (user.getEmail().equalsIgnoreCase(email)) {
                        usuarioEncontrado = user;
                        break; // Para o laço assim que encontrar o usuário
                    }
                }

                // Validação do resultado
                if (usuarioEncontrado != null) {
                    // Usuario foi encontrado
                    if (usuarioEncontrado.isBloqueado()) {
                        JOptionPane.showMessageDialog(this, "Usuario bloqueado, entre em contato com o suporte", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                    } else if (!usuarioEncontrado.getSenha().equals(senha)) {
                        JOptionPane.showMessageDialog(this, "Usuario ou senha invalido.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Se chegou aqui, o login foi bem-sucedido.
                        dispose(); // Fecha a tela de login

                        // Verifica se o usuario é um Cliente ou Administrador
                        if (usuarioEncontrado instanceof Cliente) {
                            TelaCliente telaCliente = new TelaCliente((Cliente) usuarioEncontrado);
                            telaCliente.setVisible(true);

                        } else if (usuarioEncontrado instanceof Administrador) { //
                            // Se for admin, abre a nova tela de administrador
                            TelaAdmin telaAdmin = new TelaAdmin((Administrador) usuarioEncontrado);
                            telaAdmin.setVisible(true);
                        }
                    }
                } else {
                    // Usuário não foi encontrado na lista
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                // Erro de I/O
                JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado ao ler os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Abre o painel de registro
        botaoRegistro.addActionListener(e -> {
            // MODIFICADO: Corrigido o nome da classe para TelaCadastroUsuario, que criamos anteriormente
            TelaCadastro telaCadastro = new TelaCadastro();
            telaCadastro.setVisible(true);
        });

        add(painelPrincipal);
    }
}