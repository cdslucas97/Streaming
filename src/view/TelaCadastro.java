package view;

import data.UsuarioData;
import domain.Cliente;
import domain.Usuario;
import exception.UsuarioCadastradoException;
import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

public class TelaCadastro extends JFrame {

    public TelaCadastro() {
        setTitle("MovieFlix - Cadastro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Se fechar mantem o programa rodando
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        JLabel labelNome = new JLabel("Nome de Usuário:");
        JTextField campoNome = new JTextField(15);
        JLabel labelEmail = new JLabel("Email:");
        JTextField campoEmail = new JTextField(15);
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField(15);
        JButton botaoCadastrar = new JButton("Cadastrar");

        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNome.setMaximumSize(campoNome.getPreferredSize());
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoEmail.setMaximumSize(campoEmail.getPreferredSize());
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setMaximumSize(campoSenha.getPreferredSize());
        botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Listener para o botão REGISTRAR
        botaoCadastrar.addActionListener(e -> {
            try {
                String nome = campoNome.getText();
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Usuario> usuarios = UsuarioData.listarTodos(); //

                // Exceção email cadastrado
                for (Usuario user : usuarios) {
                    if (user.getEmail().equalsIgnoreCase(email)) {
                        throw new UsuarioCadastradoException("Este endereço de email já está cadastrado.");
                    }
                }

                Usuario novoCliente = new Cliente(nome, senha, email);
                UsuarioData.adicionar(novoCliente);

                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

                // Catch de exceção e mensagem personalizada
            } catch (UsuarioCadastradoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o usuário:\n" + ex.getMessage(), "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
            }
        });

        painelPrincipal.add(Box.createVerticalGlue());
        painelPrincipal.add(labelNome);
        painelPrincipal.add(campoNome);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(labelEmail);
        painelPrincipal.add(campoEmail);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(labelSenha);
        painelPrincipal.add(campoSenha);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        painelPrincipal.add(botaoCadastrar);
        painelPrincipal.add(Box.createVerticalGlue());

        add(painelPrincipal);
    }
}