package view;
import data.ConteudoData;
import domain.Cliente;
import domain.Conteudo;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TelaCliente extends JFrame {

    private final Cliente cliente; // Armazena a instancia do cliente
    // Gerenciador JList
    private final DefaultListModel<Conteudo> catalogoListModel;
    private final DefaultListModel<Conteudo> favoritosListModel;
    private final DefaultListModel<Conteudo> historicoListModel;


    public TelaCliente(Cliente cliente) {
        this.cliente = cliente;

        // Config
        setTitle("MovieFlix - Área do Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Inicializa ListModel
        catalogoListModel = new DefaultListModel<>();
        favoritosListModel = new DefaultListModel<>();
        historicoListModel = new DefaultListModel<>();

        // Painel Superior - Bem vindo!
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelBemVindo = new JLabel("Bem-vindo(a), " + this.cliente.getNome() + "!");
        labelBemVindo.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(labelBemVindo);

        // Criação dos painéis principais
        JPanel painelCatalogo = criarPainelCatalogo();
        JPanel painelUsuario = new JPanel();
        painelUsuario.setLayout(new BoxLayout(painelUsuario, BoxLayout.Y_AXIS));
        painelUsuario.add(criarPainelFavoritos());
        painelUsuario.add(criarPainelHistorico());

        // Divisão no painel principal para organizar as listas
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelCatalogo, painelUsuario);
        splitPane.setResizeWeight(0.6); // catalogo tem 60% do tamanho da tela

        // Adiciona os componentes na janela
        add(painelSuperior, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        // Carrega os dados do catalogo
        carregarCatalogo();
    }

    // Cria o painel do catalogo
    private JPanel criarPainelCatalogo() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(new TitledBorder("Catálogo Completo"));

        JList<Conteudo> listaCatalogo = new JList<>(catalogoListModel);
        configurarLista(listaCatalogo);

        JButton botaoAssistir = new JButton("Assistir");
        JButton botaoAddFavorito = new JButton("Adicionar aos Favoritos");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAssistir);
        painelBotoes.add(botaoAddFavorito);

        // Ação do botão assistir
        botaoAssistir.addActionListener(e -> {
            Conteudo selecionado = listaCatalogo.getSelectedValue();
            if (selecionado != null) {
                // Registra a reprodução no histórico do cliente e atualiza a lista
                cliente.registarReproducao(selecionado); //
                historicoListModel.addElement(selecionado);

                // Lógica para simular a abertura de uma midia (não criamos player então fica assim mesmo)
                try {
                    String videoUrl = "https://www.youtube.com/watch?v=a3ICNMQW7Ok"; // URL para teste
                        Desktop.getDesktop().browse(new URI(videoUrl));
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao tentar abrir o link: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um item do catálogo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Ação do botão para adicionar um conteudo aos favoritos
        botaoAddFavorito.addActionListener(e -> {
            Conteudo selecionado = listaCatalogo.getSelectedValue();
            if (selecionado != null) {
                // Adiciona o favorito
                String resultado = cliente.adicionarFavorito(selecionado); //

                // Se a adição acontece, atualiza a lista da UI para evitar duplicatas visuais
                if (resultado.toLowerCase().contains("adicionado") && !favoritosListModel.contains(selecionado)) {
                    favoritosListModel.addElement(selecionado);
                }
                JOptionPane.showMessageDialog(this, resultado, "Favoritos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um item do catálogo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        painel.add(new JScrollPane(listaCatalogo), BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        return painel;
    }

    // Cria o painel de favoritos
    private JPanel criarPainelFavoritos() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(new TitledBorder("Seus Favoritos"));
        JList<Conteudo> listaFavoritos = new JList<>(favoritosListModel);
        configurarLista(listaFavoritos);
        painel.add(new JScrollPane(listaFavoritos), BorderLayout.CENTER);
        return painel;
    }

    // Cria o painel de historico
    private JPanel criarPainelHistorico() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(new TitledBorder("Seu Histórico de Reprodução"));
        JList<Conteudo> listaHistorico = new JList<>(historicoListModel);
        configurarLista(listaHistorico);

        JButton botaoLimparHistorico = new JButton("Limpar Histórico");
        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoLimparHistorico);

        // Ação do botão para limpar o histórico
        botaoLimparHistorico.addActionListener(e -> {
            // Chama o metodo do cliente para limpar a lista interna
            String resultado = cliente.limparHistorico(); //

            // Se o histórico foi limpo, atualiza a lista na UI
            if (resultado.toLowerCase().contains("limpo")) {
                historicoListModel.clear();
            }
            JOptionPane.showMessageDialog(this, resultado, "Limpeza de Histórico", JOptionPane.INFORMATION_MESSAGE);
        });

        painel.add(new JScrollPane(listaHistorico), BorderLayout.CENTER);
        painel.add(painelBotao, BorderLayout.SOUTH); // Adiciona o botão na parte de baixo
        return painel;
    }

    // Configuracao visual das nossas listas
    private void configurarLista(JList<Conteudo> lista) {
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    // Carrega o catalogo
    private void carregarCatalogo() {
        try {
            List<Conteudo> conteudos = ConteudoData.listarTodos(); //
            for (Conteudo c : conteudos) {
                catalogoListModel.addElement(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o catálogo de conteúdos:\n" + e.getMessage(), "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}