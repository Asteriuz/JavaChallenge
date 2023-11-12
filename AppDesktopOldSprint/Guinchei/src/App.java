import java.awt.Font;
import java.util.HashMap;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;

import br.com.fiap.models.*;

public class App {
    static boolean debugApp = true;

    public static void main(String[] args) throws Exception {
        Sistema sb = new Sistema();
        Usuario usuarioLogado = null;

        HashMap<String, Object> themeConfig = new HashMap<>();
        themeConfig.put("theme", "Flat Darcula");
        themeConfig.put("font", "Segoe UI");
        themeConfig.put("fontSize", "14");

        FlatDarculaLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        UIManager.getLookAndFeelDefaults()
                .put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));

        sb.loadData();

        if (debugApp) {
            usuarioLogado = sb.buscarUsuario("augustobb@live.com");
        } else {
            usuarioLogado = Interface.login(sb);
        }

        while (true) {
            int opcao = Interface.menu(usuarioLogado);
            switch (opcao) {
                case -1:
                    System.exit(0);
                    break;
                case 0:
                    sb.saveData();
                    System.exit(0);
                    break;
                case 1:
                    Interface.solicitarGuincho(usuarioLogado, sb);
                    break;
                case 2:
                    Interface.listarGuinchos();
                    break;
                case 3:
                    Interface.contato();
                    break;
                case 4:
                    Interface.verMeusDados(usuarioLogado);
                    break;
                case 5:
                    usuarioLogado = Interface.login(sb);
                    break;
                case 6:
                    Interface.settings(sb, themeConfig);
                    break;
                default:
                    System.exit(0);
                    break;

            }
        }

    }
}
