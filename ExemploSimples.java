// EXEMPLO SIMPLES DE JAVA - PARA ENTENDER OS CONCEITOS BÁSICOS

public class ExemploSimples {
    
    // MÉTODO PRINCIPAL - onde o programa começa
    public static void main(String[] args) {
        
        System.out.println("=== APRENDENDO JAVA ===\n");
        
        // 1. VARIÁVEIS SIMPLES
        String nome = "Rafael";
        int idade = 25;
        double altura = 1.75;
        
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Altura: " + altura + "m\n");
        
        // 2. ARRAY (lista)
        String[] frutas = {"Maçã", "Banana", "Laranja"};
        System.out.println("Minhas frutas favoritas:");
        for (int i = 0; i < frutas.length; i++) {
            System.out.println("- " + frutas[i]);
        }
        System.out.println();
        
        // 3. IF/ELSE (condições)
        if (idade >= 18) {
            System.out.println("Você é maior de idade!");
        } else {
            System.out.println("Você é menor de idade!");
        }
        
        // 4. LOOP (repetição)
        System.out.println("\nContando até 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
        
        // 5. CHAMANDO UM MÉTODO
        String mensagem = criarMensagem(nome);
        System.out.println("\n" + mensagem);
    }
    
    // MÉTODO PERSONALIZADO (como uma "função")
    public static String criarMensagem(String nome) {
        return "Olá " + nome + "! Bem-vindo ao mundo Java!";
    }
}

/*
EXPLICAÇÃO DAS PARTES:

1. public class ExemploSimples
   - "public" = pode ser usado por qualquer um
   - "class" = estamos criando uma classe (molde)
   - "ExemploSimples" = nome da classe

2. public static void main(String[] args)
   - "public static" = método especial que o Java procura
   - "void" = não retorna nada
   - "main" = nome do método principal
   - "String[] args" = argumentos da linha de comando

3. System.out.println()
   - "System.out" = saída padrão (tela)
   - "println" = imprimir e pular linha

4. Tipos de dados:
   - String = texto
   - int = número inteiro
   - double = número decimal
   - boolean = verdadeiro/falso

5. Estruturas:
   - if/else = se/então
   - for = para cada
   - while = enquanto
   - switch = escolha entre opções
*/ 