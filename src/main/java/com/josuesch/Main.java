package com.josuesch;

public class Main {
    public static void main(String[] args) {
        String[] testes = {
                "5 + 3 * [(2 - 1)]",  // VÁLIDA
                "5 + * 3",            // INVÁLIDA (dois operadores seguidos)
                "(5 + 3) / / 2",      // INVÁLIDA (dois operadores seguidos)
                "+ 5 - 2",            // INVÁLIDA (começa com operador)
                "5 + 3 -",            // INVÁLIDA (termina com operador)
                "5 + ( * 3)",         // INVÁLIDA (operador após abertura)
                "5 + (3 - )",         // INVÁLIDA (fechamento após operador)
                "{5 + [3 * (2 / 2)]}" // VÁLIDA
        };
        System.out.println("--- Validador de Expressões ---");
        for (String string : testes) {
            System.out.printf("Expressão: %-25s -> %s\n", string,
                    (isValida(string) ? "VÁLIDA" : "INVÁLIDA"));
        }
    }

    private static boolean isValida(String string) {
        Pilha pilha = new Pilha(string.length()/2);
        Character caractereAnterior = null;

        for (int i = 0; i < string.length(); i++) {
            char caractere = string.charAt(i);
            if (caractere == ' ') continue;
            if(isAbre(caractere)){
                //Tenta botar no topo da fila, se não conseguiu é porque abriu mais simbulos que consegue fechar então falha
                if(!pilha.push(caractere)) return false;
            }

            //Se receber um fecha pega o ultimo da pilha e ve se ta fechando o correto, se não falha
            if(isFecha(caractere)){
                Character topoDaPinha = pilha.pop();
                if(topoDaPinha == null) return false;
                switch (caractere) {
                    case ')':
                        if (topoDaPinha != '(') return false;
                        break;
                    case  ']':
                        if (topoDaPinha != '[') return false;
                        break;
                    case '}':
                        if (topoDaPinha != '{') return false;
                        break;
                }
                if(isOperador(caractereAnterior))return false;
            }
            if(isOperador(caractere)){
                if(caractereAnterior == null) return false;
                if(isAbre(caractereAnterior)) return false;
                if(isOperador(caractereAnterior)) return false;
            }

            caractereAnterior = caractere;
        }
        if (caractereAnterior == null) return false;
        if (isOperador(caractereAnterior)) return false;
        return pilha.pilhaVazia();
    }

    private static boolean isAbre(char caractere) {
        return caractere == '(' || caractere == '[' || caractere == '{';
    }

    private static boolean isFecha(char caractere) {
        return caractere == ')' || caractere == ']' || caractere == '}';
    }

    private static boolean isOperador(Character caractere) {
        return caractere != null && (caractere == '+' || caractere == '-' || caractere == '*'  || caractere == '/');
    }

}