import java.util.ArrayList;
import java.util.List;

public class Rover {

    public static void main(String[] args) {

        List<String> comandos = new ArrayList<>();
        comandos.add("DIREITA");
        comandos.add("DIREITA");
        comandos.add("ESQUERDA");
        comandos.add("DIREITA");
        comandos.add("ESQUERDA");
        comandos.add("ESQUERDA");
        comandos.add("DIREITA");
        comandos.add("DIREITA");
        comandos.add("DIREITA");

        int posicaoAtual = 0;

        for (String comando : comandos) {
            if (comando.equals("DIREITA")) {
                posicaoAtual += 1;
            } else if (comando.equals("ESQUERDA")) {
                posicaoAtual -= 1;
            }
        }

        System.out.println("Posição final do rover: " + posicaoAtual);
    }
}
