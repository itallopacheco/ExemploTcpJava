/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPServer{
    public static void main(String[] args){

        try {
            Socket sock = iniciarServidor(3300, 5, "127.0.0.1");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            byte[] buf = new byte[20]; // buffer de recepção
            
            while (true){
                receberMensagem(is,buf);
                enviarMensagem(os);
            }
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
    private static Socket iniciarServidor(int port, int backlog, String ipAddress) throws IOException {
        System.out.print("[ Iniciando Servidor TCP    .........................  ");
        ServerSocket ss = new ServerSocket(port, backlog, InetAddress.getByName(ipAddress));
        System.out.println("[OK] ]");
        System.out.print("[ Aguardando pedidos de conexão    ..................  ");
        Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
        System.out.println("[OK] ]");
        return sock;
    }
    
    private static void receberMensagem(InputStream is, byte[] buf)throws IOException {
        System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
        is.read(buf); // Operação bloqueante (aguardando chegada de dados)
        System.out.println("[OK] ]");
        String msg = new String(buf); // Mapeando vetor de bytes recebido para String
        System.out.println("  Mensagem recebida: "+ msg);
    }
    
    private static void enviarMensagem(OutputStream os)throws IOException {
        System.out.print("[ Enviando mensagem    ..............................  ");
        String msgResposta = lerMensagem();
        byte[] buf_resposta = msgResposta.getBytes(); // Obtendo a respresntação em bytes da mensagem
        os.write(buf_resposta);
    } 
    private static String lerMensagem (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a mensagem a ser enviada: ");
        return scanner.nextLine();
    }

}