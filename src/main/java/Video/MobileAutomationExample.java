package Video;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MobileAutomationExample {

    public static void main(String[] args) {

        try {
            // Caminho para o interpretador Python e o script a ser executado
            String pythonInterpreter = "python";
            String pythonScript = "C:\\Users\\fabiano.silva\\Desktop\\web_can.py";

            // Criação do processo para executar o script Python
            Process process = Runtime.getRuntime().exec(pythonInterpreter + " " + pythonScript);

            // Leitura da saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Espera o término do processo
            int exitCode = process.waitFor();
            System.out.println("Script Python finalizado com código de saída: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
