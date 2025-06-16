package view;

import domain.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class TelaAdmin extends JFrame {

    private final Administrador admin;

    // Gerenciar conteudos
    private final DefaultListModel<Conteudo> catalogoListModel;
    private final JComboBox<String> tipoConteudoComboBox;
    private final JTextField campoTitulo, campoAno, campoGenero, campoTipo;
    private final JLabel labelTipo;

    // Gerenciar usuarios
    private final DefaultListModel<Usuario> usuariosListModel;
    private JList<Usuario> listaUsuarios;

    public TelaAdmin(Administrador admin) {
        this.admin = admin;
        this.catalogoListModel = new DefaultListModel<>();
        this.usuariosListModel = new DefaultListModel<>();

        // Inicia os componentes do formulário
        tipoConteudoComboBox = new JComboBox<>(new String[]{"Filme", "Série"});
        campoTitulo = new JTextField(20);
        campoAno = new JTextField(5);
        campoGenero = new JTextField(15);
        labelTipo = new JLabel("Duração (minutos):");
        campoTipo = new JTextField(5);

        // Configuração da janela
        setTitle("MovieFlix - Painel do Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel superior com o nome do admin
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelBemVindo = new JLabel("Administrador");
        labelBemVindo.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(labelBemVindo);

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Gerenciar Conteúdo", criarPainelGerenciarConteudo());
        abas.addTab("Gerenciar Usuários", criarPainelGerenciarUsuarios());

        add(painelSuperior, BorderLayout.NORTH);
        add(abas, BorderLayout.CENTER);

        // Carregamento dos dados
        carregarCatalogo();
        carregarUsuarios();
    }

    // Gerenciar conteudo
    private JPanel criarPainelGerenciarConteudo() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel da lista do catálogo
        JPanel painelCatalogo = new JPanel(new BorderLayout());
        painelCatalogo.setBorder(new TitledBorder("Catálogo Atual"));
        JList<Conteudo> listaCatalogo = new JList<>(catalogoListModel);
        listaCatalogo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        painelCatalogo.add(new JScrollPane(listaCatalogo), BorderLayout.CENTER);

        // Painel do formulário na vertical
        JPanel painelAdicionar = new JPanel();
        painelAdicionar.setLayout(new BoxLayout(painelAdicionar, BoxLayout.Y_AXIS));
        painelAdicionar.setBorder(new TitledBorder("Adicionar Novo Conteúdo ao Catálogo"));

        // Linhas do formulário
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha1.add(new JLabel("Tipo:"));
        linha1.add(tipoConteudoComboBox);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha2.add(new JLabel("Título:"));
        linha2.add(campoTitulo);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha3.add(new JLabel("Ano:"));
        linha3.add(campoAno);
        linha3.add(new JLabel("Gênero:"));
        linha3.add(campoGenero);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha4.add(labelTipo);
        linha4.add(campoTipo);

        JPanel linhaBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botaoAdicionar = new JButton("Adicionar ao Catálogo");
        linhaBotao.add(botaoAdicionar);

        // Adiciona as linhas ao painel
        painelAdicionar.add(linha1);
        painelAdicionar.add(linha2);
        painelAdicionar.add(linha3);
        painelAdicionar.add(linha4);
        painelAdicionar.add(linhaBotao);

        // Listener combobox (altera entre filme e série e modifica componente do usuario)
        tipoConteudoComboBox.addActionListener(e -> {
            labelTipo.setText("Filme".equals(tipoConteudoComboBox.getSelectedItem()) ? "Duração (minutos):" : "Nº de Temporadas:");
        });

        // Listener botao adicionar
        botaoAdicionar.addActionListener(e -> adicionarConteudo());

        painelPrincipal.add(painelCatalogo, BorderLayout.CENTER);
        painelPrincipal.add(painelAdicionar, BorderLayout.SOUTH);
        return painelPrincipal;
    }

    // Painel gerenciamento de usuario
    private JPanel criarPainelGerenciarUsuarios() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listaUsuarios = new JList<>(usuariosListModel);
        painel.add(new JScrollPane(listaUsuarios), BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 linhas, 2 colunas, com espaçamento
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Margem superior

        JButton botaoAdicionarUsuario = new JButton("Adicionar Novo Usuário");
        JButton botaoBloquearUsuario = new JButton("Bloquear/Desbloquear");
        JButton botaoExcluirUsuario = new JButton("Excluir Usuário");
        JButton botaoAtualizarLista = new JButton("Atualizar Lista");

        painelBotoes.add(botaoAdicionarUsuario);
        painelBotoes.add(botaoExcluirUsuario);
        painelBotoes.add(botaoBloquearUsuario);
        painelBotoes.add(botaoAtualizarLista);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        botaoAdicionarUsuario.addActionListener(e -> {
            // Chama TelaCadastrada pra cadastrar usuario
            new TelaCadastro().setVisible(true);
        });

        // Listener para botão que alterna status do usuario
        botaoBloquearUsuario.addActionListener(e -> {
            Usuario user = listaUsuarios.getSelectedValue();
            if (user == null) return;
            try {
                String resultado = user.isBloqueado()
                        ? admin.desbloquearUsuario(user)
                        : admin.bloquearUsuario(user);
                JOptionPane.showMessageDialog(this, resultado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Atualiza lista de usuarios pra mostrar o novo status
                carregarUsuarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar status: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener para o botão que exclui usuarios
        botaoExcluirUsuario.addActionListener(e -> {
            Usuario selecionado = listaUsuarios.getSelectedValue();
            if (selecionado == null) return;
            // Cria uma janela para confirmar se deseja realmente excluir o usuario
            int confirm = JOptionPane.showConfirmDialog(this, "Excluir '" + selecionado.getNome() + "'?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String resultado = admin.excluirUsuario(selecionado);
                    JOptionPane.showMessageDialog(this, resultado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    carregarUsuarios();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botão para atualizar a lista de usuarios
        botaoAtualizarLista.addActionListener(e -> carregarUsuarios());
        return painel;
    }

    // ----- Metodos da interface -----
    // Metodo que adiciona o conteudo na lista (****)
    private void adicionarConteudo() {
        try {
            String titulo = campoTitulo.getText();
            String ano = campoAno.getText();
            String genero = campoGenero.getText();
            if (titulo.isEmpty() || ano.isEmpty() || genero.isEmpty() || campoTipo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int valorTipo = Integer.parseInt(campoTipo.getText());
            Conteudo novoConteudo;
            if ("Filme".equals(tipoConteudoComboBox.getSelectedItem())) {
                novoConteudo = new Filme(titulo, ano, genero, valorTipo);
            } else {
                novoConteudo = new Serie(titulo, ano, genero, valorTipo);
            }
            String resultado = admin.adicionarConteudo(novoConteudo);
            catalogoListModel.addElement(novoConteudo);
            limparCamposConteudo();
            JOptionPane.showMessageDialog(this, resultado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o conteúdo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para carregar a lista do catalogo de conteudos
    private void carregarCatalogo() {
        catalogoListModel.clear();
        try {
            List<Conteudo> conteudos = admin.listarTodoOConteudo();
            for (Conteudo c : conteudos) {
                catalogoListModel.addElement(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o catálogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo que limpa os campos apos cadastrar o usuario
    private void limparCamposConteudo() {
        campoTitulo.setText("");
        campoAno.setText("");
        campoGenero.setText("");
        campoTipo.setText("");
    }

    // Metodo para carregar a lista de usuarios
    private void carregarUsuarios() {
        usuariosListModel.clear();
        try {
            List<Usuario> usuarios = admin.listarTodosOsUsuarios();
            for (Usuario user : usuarios) {
                usuariosListModel.addElement(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}