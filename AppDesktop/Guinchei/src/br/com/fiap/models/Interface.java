package br.com.fiap.models;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Interface {
    public static Usuario login(Sistema sb) {
        ImageIcon icon = new ImageIcon("GFX/images/GuinchoAzul.png");
        while (true) {
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setPreferredSize(new java.awt.Dimension(200, 100));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            panel.add(new JLabel("Digite seu email:"), gbc);
            gbc.gridy++;
            JTextField textField = new JTextField(10);
            textField.setPreferredSize(new java.awt.Dimension(200, 32));
            panel.add(textField, gbc);
            int result = JOptionPane.showOptionDialog(null, panel, "Guinchei",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    icon, new String[] { "OK", "Cadastrar", "Cancelar" }, "OK");
            if (result == -1) {
                System.exit(0);
            }
            if (result == 2) {
                System.exit(0);
            }

            if (result == 1) {
                cadastrar(sb);
            }
            if (result == 0) {

                String email = textField.getText();
                if (email == null) {
                    System.exit(0);
                }
                String senha = (String) JOptionPane.showInputDialog(null, "Digite sua senha:", "Guinchei",
                        JOptionPane.QUESTION_MESSAGE, icon, null, null);
                if (senha == null) {
                    System.exit(0);
                }
                Usuario usuario = sb.buscarUsuario(email);
                if (usuario == null || !usuario.getSenha().equals(senha)) {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
                    continue;
                }
                return usuario;
            }
        }
    }

    public static void cadastrar(Sistema sb) {
        while (true) {

            ImageIcon icon = new ImageIcon("GFX/images/GuinchoAzul.png");
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setPreferredSize(new java.awt.Dimension(200, 250));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            panel.add(new JLabel("Digite seu nome:"), gbc);
            gbc.gridy++;
            JTextField textField = new JTextField(10);
            textField.setPreferredSize(new java.awt.Dimension(200, 32));
            panel.add(textField, gbc);
            gbc.gridy++;
            panel.add(new JLabel("Digite seu email:"), gbc);
            gbc.gridy++;
            JTextField textField2 = new JTextField(10);
            textField2.setPreferredSize(new java.awt.Dimension(200, 32));
            panel.add(textField2, gbc);
            gbc.gridy++;
            panel.add(new JLabel("Digite sua senha:"), gbc);
            gbc.gridy++;
            JTextField textField3 = new JTextField(10);
            textField3.setPreferredSize(new java.awt.Dimension(200, 32));
            panel.add(textField3, gbc);
            gbc.gridy++;
            panel.add(new JLabel("(Opcional) Adicione uma foto:"), gbc);
            gbc.gridy++;
            JButton button = new JButton("Selecionar");
            button.setPreferredSize(new java.awt.Dimension(200, 32));
            panel.add(button, gbc);
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            button.addActionListener(e -> {
                file.showOpenDialog(null);
                if (file.getSelectedFile() != null) {
                    button.setText(file.getSelectedFile().getName());
                }
            });
            gbc.gridy++;
            int result = JOptionPane.showOptionDialog(null, panel, "Guinchei",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    icon, new String[] { "OK", "Cancelar" }, "OK");

            if (result == 1) {
                System.exit(0);
            }

            String nome = textField.getText();
            String email = textField2.getText();
            String senha = textField3.getText();
            if (nome == null || email == null || senha == null) {
                System.exit(0);
            }
            if (sb.buscarUsuario(email) != null) {
                JOptionPane.showMessageDialog(null, "Email já cadastrado");
                continue;
            }

            if (file.getSelectedFile() != null) {
                sb.addUsuario(
                        new Usuario(nome, email, senha,
                                file.getSelectedFile().getAbsolutePath()));
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                sb.saveData();
                return;
            }
            sb.addUsuario(
                    new Usuario(nome, email, senha,
                            "GFX/profiles/defaultAvatar.png"));
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
            sb.saveData();
            return;
        }
    }

    public static int menu(Usuario usuario) {
        String info = "";
        String menu = "0 - Sair\n1 - Solicitar Guincho\n2 - Ver Guinchos Disponíveis\n3 - Contato\n4 - Ver meus dados\n5 - Deslogar\n6 - Configurações\n\n";
        int opcao = JOptionPane.showOptionDialog(null, (info + menu), "Guinchei - " + usuario.getNome(), 0,
                JOptionPane.QUESTION_MESSAGE, usuario.getFoto(),
                new String[] { "0", "1", "2", "3", "4", "5", "6" }, "1");
        return opcao;
    }

    public static void contato() {
        JOptionPane.showMessageDialog(null,
                "Assistência pelo WhatsApp\n\n55 11 3003 9303\nPara pedir assistência, adicione o WhatsApp da Porto aos seus contatos e siga as orientações da nossa assistente virtual.\n\nCentral de serviços\nGrande São Paulo: 333 76786\nCapitais e regiões metropolitanas: 4004 76786\nDemais localidades: 0300 337 6786\nAviso de sinistro, solicitação de serviços para automóvel, residência e outros. Atendimento 24h.\n\nSAC\n0800 727 2766\nAtendimento 24h.\n\n0800 727 8736\nAtendimento exclusivo para pessoas com deficiência auditiva. De segunda a sexta-feira, das 6h às 22h.");
    }

    public static void listarGuinchos() {
        JOptionPane.showMessageDialog(null,
                "Guinchos Disponíveis:\nGUINCHO PESADO NAO PADRAO\nPESADO COM PLAT HIDRAULICA MUNCK\nPESADO COM PLATAFORMA HIDRAULICA\nPESADO COM PLATAFORMA HIDRAULICA E BAND\nPESADO COM QUINTA RODA E BANDEJA\nPESADO COM TORRE E LANCA\nPESADO COM PLATAFORMA HIDRAULICA\nPESADO COM PLATAFORMA HIDRAULICA E LANCA\nPESADO COM QUINTA RODA E LANCA");
    }

    public static void verMeusDados(Usuario usuario) {
        JOptionPane.showMessageDialog(null,
                "Nome: " + usuario.getNome() + "\nEmail: " + usuario.getEmail());
    }

    public static void solicitarGuincho(Usuario usuario, Sistema sb) {

        JLabel label = new JLabel("Descreva o acidente:");
        JTextArea acidenteText = new JTextArea();
        acidenteText.setLineWrap(true);
        acidenteText.setWrapStyleWord(true);
        acidenteText.setPreferredSize(new Dimension(200, 48));
        JScrollPane scrollPane = new JScrollPane(acidenteText);
        JLabel localLabel = new JLabel("Digite sua localização:");
        JTextField localText = new JTextField(10);
        JLabel codigoLabel = new JLabel("Digite o código tarifário do seu veículo:");
        JSpinner codigoNumber = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(label, gbc);

        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(localLabel, gbc);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(localText, gbc);

        gbc.gridy = 4;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(codigoLabel, gbc);

        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(codigoNumber, gbc);

        int opcao = JOptionPane.showOptionDialog(null, panel, "Guinchei",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                usuario.getFoto(), new String[] { "OK", "Lista de Código Tarifário", "Cancelar" }, "OK");
        if (opcao == 0) {
            Integer codigo = (Integer) codigoNumber.getValue();
            if (acidenteText.getText().equals("") || localText.getText().equals("")
                    || codigoNumber.getValue().equals(0)) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                Interface.solicitarGuincho(usuario, sb);
                return;
            }
            if (acidenteText.getText().contains(";") || localText.getText().contains(";")) {
                JOptionPane.showMessageDialog(null, "Caracter inválido: ;");
                Interface.solicitarGuincho(usuario, sb);
                return;
            }
            if (sb.getCategoria().getCategoria(codigo) == null) {
                JOptionPane.showMessageDialog(null,
                        "Código Tarifário não encontrado, veja a lista de Código Tarifário");
                Interface.solicitarGuincho(usuario, sb);
                return;
            }
            String tipoVeiculo = sb.getCategoria().getCategoria(codigo);
            JOptionPane.showMessageDialog(null,
                    "Estamos revisando o caso e enviaremos o guincho o mais rápido possível\n\n" + "Acidente: "
                            + acidenteText.getText().trim() + "\nLocalização: " + localText.getText()
                            + "\nCódigo Tarifário: "
                            + codigoNumber.getValue() + "\nTipo de Veículo: " + tipoVeiculo + "\nCategoia: "
                            + sb.getCategoria().getTipoCodigo(codigo) + "\n\nObrigado por escolher a Porto Seguro!");

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = formatador.format(data);

            sb.getCasos().add(new Caso(acidenteText.getText().trim(), localText.getText(), dataFormatada, tipoVeiculo,
                    "pendente", usuario));
            sb.saveData();
            return;
        }
        if (opcao == 1) {
            Map<Integer, String> listCategorias = sb.getCategoria().listarCategorias();
            String[] colunas = { "Nacional", "Importado", "Tipo de Veículo" };
            Object[][] dados = new Object[sb.getCategoria().listarCategoriasSemRepeticao().size()][colunas.length];
            int i = 0;
            List<String> listaRepetidos = new ArrayList<>();
            for (Map.Entry<Integer, String> entry : listCategorias.entrySet()) {
                if (!listaRepetidos.contains(entry.getValue())) {
                    listaRepetidos.add(entry.getValue());
                    if (sb.getCategoria().getTipoCodigo(entry.getKey()).equals("NACIONAL")) {
                        dados[i][0] = entry.getKey();
                    } else {
                        dados[i][1] = entry.getKey();
                    }
                    dados[i][2] = entry.getValue();
                    i++;
                } else {
                    dados[i - 1][1] = entry.getKey();
                }
            }
            JTable tabela = new JTable(dados, colunas);
            tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
            tabela.setFillsViewportHeight(true);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(2600);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tabela.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

            tabela.setDefaultEditor(Object.class, null);
            JScrollPane scrollPane2 = new JScrollPane(tabela, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane2.setPreferredSize(new Dimension(scrollPane2.getPreferredSize().width + 500,
                    scrollPane2.getPreferredSize().height + 20));
            JOptionPane.showMessageDialog(null, scrollPane2);
            Interface.solicitarGuincho(usuario, sb);
        }
        if (opcao == 2) {
            return;
        }

    }

    public static void settings(Sistema sb, Map<String, Object> themeConfig) {
        String[] themes = { "Flat Dark", "Flat Light", "Flat IntelliJ", "Flat Lighter", "Flat Darcula" };
        String[] fontFamilies = { "Segoe UI", "Arial", "Calibri", "Cambria", "Consolas", "Courier New", "Georgia",
                "Helvetica", "Lucida Console", "Tahoma", "Times New Roman", "Verdana" };
        String[] fontSizes = { "10", "12", "14", "16", "18", "20", "22", "24" };

        JComboBox<String> themeBox = new JComboBox<>(themes);
        JComboBox<String> fontBox = new JComboBox<>(fontFamilies);
        JComboBox<String> fontSizeBox = new JComboBox<>(fontSizes);

        themeBox.setSelectedItem(themeConfig.get("theme"));
        fontBox.setSelectedItem(themeConfig.get("font"));
        fontSizeBox.setSelectedItem(themeConfig.get("fontSize"));

        JLabel themeLabel = new JLabel("Theme");
        JLabel fontLabel = new JLabel("Font Family");
        JLabel fontSizeLabel = new JLabel("Font Size");

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(themeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(themeBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(fontLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(fontBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(fontSizeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(fontSizeBox, gbc);

        int result = JOptionPane.showOptionDialog(null, panel, "Settings",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, new String[] { "OK", "Cancelar" }, "OK");

        if (result == 0) {
            String theme = (String) themeBox.getSelectedItem();
            String font = (String) fontBox.getSelectedItem();
            String fontSize = (String) fontSizeBox.getSelectedItem();

            if (theme.equals("Flat Dark")) {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
            } else if (theme.equals("Flat Light")) {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
            } else if (theme.equals("Flat IntelliJ")) {
                try {
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
            } else if (theme.equals("Flat Lighter")) {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
            } else if (theme.equals("Flat Darcula")) {
                try {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
            }

            UIManager.getLookAndFeelDefaults()
                    .put("defaultFont", new Font(font, Font.PLAIN, Integer.parseInt(fontSize)));

            themeConfig.put("theme", theme);
            themeConfig.put("font", font);
            themeConfig.put("fontSize", fontSize);
        }

    }
}
