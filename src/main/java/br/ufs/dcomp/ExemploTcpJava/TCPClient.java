/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient{
    public static void main(String[] args){
        try {
            Socket sock = conectarComServidor("127.0.0.1",3300);
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            byte[] buf_resposta = new byte[20]; // buffer de recepção

            while(true){
                enviarMensagem(os);
                receberMensagem(is,buf_resposta);
            }
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
    public static Socket conectarComServidor(String ip, Integer port) throws IOException {
        System.out.print("[ Conectando com o Servidor TCP    ..................  ");
        Socket sock = new Socket("127.0.0.1", 3300);
        System.out.println("[OK] ]");
        return sock;
    }
    private static void enviarMensagem(OutputStream os)throws IOException {
        System.out.print("[ Enviando mensagem    ..............................  ");
        String msgResposta = lerMensagem();
        byte[] buf_resposta = msgResposta.getBytes(); // Obtendo a respresntação em bytes da mensagem
        os.write(buf_resposta);
    } 
    private static void receberMensagem(InputStream is, byte[] buf)throws IOException {
        System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
        is.read(buf); // Operação bloqueante (aguardando chegada de dados)
        System.out.println("[OK] ]");
        String msg = new String(buf); // Mapeando vetor de bytes recebido para String
        System.out.println("  Mensagem recebida: "+ msg);
    }
    private static String lerMensagem (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a mensagem a ser enviada: ");
        return scanner.nextLine();
    }
}