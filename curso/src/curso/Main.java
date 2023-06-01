import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);

            System.out.print("Digite a Base: ");
            int base = scan.nextInt();
            System.out.print("Digite o Expoente: ");
            int expoente = scan.nextInt();

            int resultado;

            if (expoente == 0) {
                resultado = 1;
            } else  if (expoente == 1) {
                resultado = base;
            } else {
                resultado = base;
                for (int i = 1; i < expoente; i++) {
                    resultado = multiplicar(resultado, base);
                }
            }
      
            System.out.println("Resultado 1: " + resultado);

            System.out.println("Resultado 2: " + potencializar(base, expoente));

            System.out.println("Conferência: " + Math.pow(base, expoente));
        }

        public static int multiplicar(int multiplicando, int multiplicador) {
            int produto = 0;
            for (int i = 0; i < multiplicador; i++) {
                produto += multiplicando;
            }
            return produto;
        }

        public static int potencializar(int base, int expoente) {
            int resultado = base; //inicializa com o valor da base
            int contador = base;

            if (expoente == 0) resultado = 1; //Quando expoente for 0, então o resultado será 1

            for (int p = 1; p < expoente; p++) {       //vai executar a quantidade vezes do expoente - 1
                for (int i = 1; i < contador; i++) {   //vai executar a quantidade de vezes da base - 1, pois o resultado já inicia com o valor da base
                    resultado += base; 
                }
                base = resultado;
            }

            return resultado;
        }

    }
