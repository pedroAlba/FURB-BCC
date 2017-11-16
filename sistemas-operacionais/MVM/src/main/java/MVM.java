
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author Guilherme Bacca, Peterson Boni
 */
public class MVM {

	private static int acessosMemoria;
    private static TelaExecucao tela;
    public static int botao = 0;
    static int ax = 0, bx = 0, cx = 0, bp = 0, sp = 0, ip, ri;
    static int iPosicaoInstrucoes = 0; //para pegar do arraylist a instruçao que esta sendo executada
    static int iValorInicialPilha = -1;
	public static boolean halt;

    private static String converterASCII(int ax) {
        char ch = (char) ax;
        return ch+"";
    }
    
    public MVM(TelaExecucao telaExecucao){
        tela = telaExecucao;
    }
    
    public static void zeraRegs(){
        ax = 0; 
        bx = 0; 
        cx = 0;
        bp = 0; 
        sp = 0;
        ip = 0; 
        ri = 0;
        iPosicaoInstrucoes = 0;
    }
    
    public int getAcessosMemoria() {
		return acessosMemoria;
	}
    
    public static void decodificador(short mem[], int programa, int aux, ArrayList<String> arrayInstrucoes) {
        boolean repetir = true;
        if (halt) {
        	repetir = false;
        }
        ip = ip + aux; //ganha ele mesmo +1 para salvar quando dar step
        aux = 0;
        
        while (repetir) {
            
            System.out.println("Valor de IP: " + ip);
            if(iPosicaoInstrucoes >= arrayInstrucoes.size()){ //termina o programa se chegou na ultima instrucao e nao encontrou halt, nao ocorre no Step
                JOptionPane.showMessageDialog(null, "O programa não possui 'halt' para terminar.\nExecução finalizada");
                repetir = false;
            }
            else
            	if (iPosicaoInstrucoes >= 0 && aux==0) {
            		tela.setEdtLinhaExecucao(arrayInstrucoes.get(iPosicaoInstrucoes++));
            	}            
            if (botao == 1) {
                    //"push ip" 
                    mem[sp] = (short) ip;
                    sp--;

                    //"push bp" 
                    mem[sp] = (short) bp;
                    sp--;

                    //"push ax" 
                    mem[sp] = (short) ax;
                    sp--;

                    //"push bx" 
                    mem[sp] = (short) bx;
                    sp--;

                    //"push cx" 
                    mem[sp] = (short) cx;
                    sp--;

                    ip = mem[0];
                    iPosicaoInstrucoes = ip;
                    botao = 0;
                    tela.appendLog("EXECUTOU INTERRUPÇAO: INT3");
                    System.out.println("EXECUTOU INTERRUPCAO: INT3");
            }

            ri = mem[ip];
            int auxIp = 0;
            switch (ri) {
                case 0:// "init ax"
                    tela.appendLog(ip+" - Executou init ax");
                    ax = 0;
                    acessosMemoria++;
                    break;

                case 1:// "move ax,bx"
                    tela.appendLog(ip+" - Executou move ax,bx");
                    ax = bx;
                    acessosMemoria++;
                    break;
                case 2:// "move ax,cx",
                    tela.appendLog(ip+" - Executou move ax,cx");
                    ax = cx;
                    acessosMemoria++;
                    break;

                case 3:// "move bx,ax"
                    tela.appendLog(ip+" - Executou move bx,ax");
                    bx = ax;
                    acessosMemoria++;
                    break;

                case 4:// "move cx,ax"
                    tela.appendLog(ip+" - Executou move cx,ax");
                    cx = ax;
                    acessosMemoria++;
                    break;

                case 5:// "move ax,[",
                    ax = mem[mem[ip + 1]];
                    tela.appendLog("Executou move ax,[" + mem[ip + 1] + "]");
                    System.out.println("Executou move ax,[" + mem[ip + 1] + "]");
                    ip++;
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 6:// "move ax,[bx+"
                    ax = mem[bx+mem[ip+1]];
                    tela.appendLog("Executou move ax, [bx+" + mem[ip+1]+ "]");
                    System.out.println("Executou move ax, [bx+" + mem[ip+1]+ "]");
                    ip++;
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 7:// "move ax,[bp-"
                    ax = mem[bp - mem[ip+1]];
                    tela.appendLog("Executou move ax, [bx-" + mem[ip+1]+ "]");
                    System.out.println("Executou move ax, [bx-" + mem[ip+1]+ "]");
                    ip ++;
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 8://"move ax,[bp+"
                    ax = mem[bp+mem[ip+1]];
                    tela.appendLog("Executou move ax, [bp+" + mem[ip+1]+ "].");
                    System.out.println("Executou move ax, [bp+" + mem[ip+1]+ "].");
                    ip++;
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 9://"move [],ax"
                    mem[mem[ip + 1]] = (short) ax;
                    tela.appendLog("Executou move ["+ mem[ip + 1]+"],ax.");
                    System.out.println("Executou move [" + mem[ip+1]+ "],ax.");
                    ip++;
                    acessosMemoria++;
                    acessosMemoria++;
                   
                    acessosMemoria++;
                    break;

                case 10://"move [bx+"
                    mem[bx + mem[ip+1]] = (short) ax;
                    tela.appendLog("Executou move [bx+"+ mem[ip + 1]+ "],ax.");
                    System.out.println("Executou move [bx+" + mem[ip+1]+ "],ax.");
                    ip ++;
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 11://"move bp,sp"
                    tela.appendLog(ip+" - Executou move bp,sp");
                    bp = sp;
                    acessosMemoria++;
                    break;

                case 12://"move sp,bp"
                    tela.appendLog(ip+" - Executou sp,bp");
                    sp = bp;
                    acessosMemoria++;
                    break;

                case 13://"add ax,bx"
                    tela.appendLog(ip+" - Executou add ax,bx");
                    ax = ax + bx;
                    acessosMemoria++;
                    break;

                case 14://"add ax,cx"
                    tela.appendLog(ip+" - Executou add ax,cx");
                    ax = ax + cx;
                    acessosMemoria++;
                    break;

                case 15://"add bx,cx"
                    tela.appendLog(ip+" - Executou add bx,cx");
                    bx = bx + cx;
                    acessosMemoria++;
                    break;

                case 16://"sub ax,bx"
                    tela.appendLog(ip+" - Executou sub ax,bx");
                    ax = ax - bx;
                    acessosMemoria++;
                    break;

                case 17://"sub ax,cx"
                    tela.appendLog(ip+" - Executou sub ax,cx");
                    ax = ax - cx;
                    acessosMemoria++;
                    break;

                case 18://"sub bx,cx"
                    tela.appendLog(ip+" - Executou sub bx,cx");
                    bx = bx - cx;
                    acessosMemoria++;
                    break;

                case 19://"inc ax"
                    tela.appendLog(ip+" - Executou inc ax");
                    ax++;
                    acessosMemoria++;
                    break;

                case 20://"inc bx"
                    tela.appendLog(ip+" - Executou inc bx");
                    bx++;
                    acessosMemoria++;
                    break;

                case 21://"inc cx"
                    tela.appendLog(ip+" - Executou inc cx");
                    cx++;
                    acessosMemoria++;
                    break;

                case 22://"dec ax"
                    tela.appendLog(ip+" - Executou dec ax");
                    ax--;
                    acessosMemoria++;
                    break;

                case 23://"dec bx"
                    tela.appendLog(ip+" - Executou dec bx");
                    bx--;
                    acessosMemoria++;
                    break;

                case 24://"dec cx"
                    tela.appendLog(ip+" - Executou dec cx");
                    cx--;
                    acessosMemoria++;
                    break;

                case 25://"test ax0,"
                    tela.appendLog(ip+" - Executou test ax0,"+mem[ip + 1]);
                    if (ax == 0) {
                        ip = aux + mem[ip + 1] - 1; //-1 para compensar o ip++ no laco
                        iPosicaoInstrucoes = ip;
                    } else {
                        ip++;
                    }
                    acessosMemoria++;
                    acessosMemoria++;
                    break;

                case 26://"jmp "
                    ip = aux + mem[ip + 1];
                    tela.appendLog("Jmp "+ip+".");
                    System.out.println("jmp "+ip);
                    ip--;
                    iPosicaoInstrucoes = ip;
                    acessosMemoria++;
                    acessosMemoria++;
                    break;

                case 27://"call"
                    tela.appendLog(ip+" - Executou call "+(ip+2));
                    mem[sp] = (short) (ip + 2);
                    sp--;
                    ip = aux + mem[ip + 1];
                    ip--; //para compensar a alteracao de ip
                    iPosicaoInstrucoes = ip;
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 28://"ret"
                    tela.appendLog(ip+" - Executou ret");
                    sp++;
                    ip = mem[sp];
                    ip--;
                    iPosicaoInstrucoes = ip;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 29://"in ax"
                    ax = Integer.parseInt(JOptionPane.showInputDialog("ax:"));
                    tela.appendLog(ip+" - Executou in ax");
                    acessosMemoria++;
                    
                    break;

                case 30://"out ax"
                    System.out.println("Saida: AX=" + ax);
                    acessosMemoria++;
                    System.out.println("batataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    tela.printConsoleOut(converterASCII(ax),bx,cx);
                    break;

                case 31://"push ax"
                    tela.appendLog(ip+" - Executou push ax");
                    mem[sp] = (short) ax;
                    sp--;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 32://"push bx"
                    tela.appendLog(ip+" - Executou push bx");
                    mem[sp] = (short) bx;
                    sp--;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 33://"push cx"
                    tela.appendLog(ip+" - Executou push cx");
                    mem[sp] = (short) cx;
                    sp--;
                    acessosMemoria++;

                    acessosMemoria++;
                    break;

                case 34://"push bp"
                    tela.appendLog(ip+" - Executou push bp");
                    mem[sp] = (short) bp;
                    sp--;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 35://"pop bp"
                    tela.appendLog(ip+" - Executou pop bp");
                    sp++;
                    bp = mem[sp];
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 36://"pop cx"
                    tela.appendLog(ip+" - Executou pop cx");
                    sp++;
                    cx = mem[sp];
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 37://"pop bx"
                    tela.appendLog(ip+" - Executou pop bx");
                    sp++;
                    bx = mem[sp];
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 38://"pop ax"
                    tela.appendLog(ip+" - Executou pop ax");
                    sp++;
                    if(!(sp <= 0))
                        ax = mem[sp];
                    mem[sp] = 0;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 39://"nop"
                    tela.appendLog(ip+" - Executou nop");
                    acessosMemoria++;
                    break;

                case 40: //"halt"
                    tela.appendLog(ip+" - Executou halt");
                    repetir = false;
                    halt = true;
                    acessosMemoria++;
                    break;

                case 41://"dec sp"
                    tela.appendLog(ip+" - Executou dec sp");
                    sp--;
                    acessosMemoria++;
                    break;

                case 42://"move [bp-"
                    auxIp = ip;
                    mem[aux + bp - mem[ip + 1]] = (short) ax;
                    ip++;
                    tela.appendLog(auxIp+" - Executou [bp-"+(auxIp + 1)+"], ax");
                    acessosMemoria++;
                    acessosMemoria++;
                    
                    acessosMemoria++;
                    break;

                case 43://"move [bp+"
                    
                    break;

                case 44://"move ax,{"
                    auxIp = ip;
                    ax = mem[ip+1];
                    ip++;
                    tela.appendLog(auxIp+" - Executou move ax,{"+ax+"}");
                    acessosMemoria++;
                    acessosMemoria++;
                    break;

                case 45://"test axEqbx,"
                    auxIp = ip;
                    if (ax == bx) {
                        ip = mem[ip + 1]-1;
                        iPosicaoInstrucoes = ip;
                        tela.appendLog(auxIp+" - Executou THEN test axEqbx -> ip"+mem[ip+1]+".");
                        System.out.println(auxIp+" - Executou THEN test axEqbx -> ip"+ mem[ip+1]+".");
                        
                    } else {
                        ip++;
                        tela.appendLog(auxIp+" - Executou ELSE test axEqbx -> ip"+ ip+".");
                        System.out.println(auxIp+" - Executou ELSE test axEqbx -> ip" + ip+".");
                    }
                    acessosMemoria++;
                    acessosMemoria++;
                    break;

                case 46://"inc sp"
                    tela.appendLog(ip+" - Executou inc sp");
                    sp++;
                    acessosMemoria++;
                    break;

                case 47://"move ax,sp"
                    tela.appendLog(ip+" - Executou move ax,sp");
                    ax = sp;
                    acessosMemoria++;
                    break;

                case 48://"move sp,ax"   
                    tela.appendLog(ip+" - move sp,ax");
                    sp = ax;
                    iValorInicialPilha = sp; //salva esse valor para mostrar o pilha a partir dele
                    acessosMemoria++;
                    break;

                case 49://"move ax,bp"
                    tela.appendLog(ip+" - Executou move ax,bp");
                    ax = bp;
                    acessosMemoria++;
                    break;

                case 50://"move bp,ax,{"
                    tela.appendLog(ip+" - Executou move bp,ax");
                    bp = ax;
                    acessosMemoria++;
                    acessosMemoria++;
                    break;

                case 51://"iret"
                    tela.appendLog(ip+" - Executou iret");
                    //"pop cx"

                    sp++;

                    cx = mem[sp];

                    //"pop bx"

                    sp++;

                    bx = mem[sp];

                    //"pop ax"

                    sp++;

                    ax = mem[sp];

                    //"pop bp"

                    sp++;

                    bp = mem[sp];

                    //"ret"

                    sp++;

                    ip = mem[sp];

                    ip--;
                    
                    iPosicaoInstrucoes = ip;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;

                    break;

                case 52://"int"
                    tela.appendLog(ip+" - Executou int");
                    //"push ip" 
                    mem[sp] = (short) (ip + 2);
                    sp--;

                   //"push bp" 
                    mem[sp] = (short) bp;
                    sp--;

                    //"push ax" 
                    mem[sp] = (short) ax;
                    sp--;

                    //"push bx" 
                    mem[sp] = (short) bx;
                    sp--;

                    //"push cx" 
                    mem[sp] = (short) cx;
                    sp--;

                    ip = mem[aux + mem[ip + 1]];
                    ip--;

                    iPosicaoInstrucoes = ip;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    acessosMemoria++;
                    break;
                    
                case 53://"sub bx,ax"
                    tela.appendLog(ip+" - Executou sub bx,ax");
                    bx = bx - ax;
                    acessosMemoria++;
                    break;
                    
                case 54://"move [],bx"
                    mem[mem[ip + 1]] = (short) bx;
                    tela.appendLog("Executou move ["+ mem[ip + 1]+"],bx.");
                    System.out.println("Executou move [" + mem[ip+1]+ "],bx.");
                    ip++;
                    acessosMemoria++;
                    acessosMemoria++;
                   
                    acessosMemoria++;
                    break;

                    
                default: {
                    repetir = false;
                    tela.appendLog("Saiu.");
                    System.out.println("Saiu.");
                }

                if (ip >= mem.length) {
                    tela.appendLog("ERRO: a memoria nao pode ser lida.");
                    System.out.println("ERRO: a memoria nao pode ser lida.");
                    repetir = false;
                }
            }
            int spAux = iValorInicialPilha;
            tela.LimpaPilha();
            if(spAux <= 0){
                short shAux = 0;
                tela.PreenchePilha(shAux, shAux);
            }
            else{
                for (int i = 0; i < 15; i++) {
                    if(spAux < 0){
                        break;
                    }
                    tela.PreenchePilha(spAux, mem[spAux--]);
                }
            }
            
            tela.setRegistradores(ax, bx, cx, sp, bp, ip);
            
            ip++;
            if(tela.bStop){
                repetir = false;
                break;
            }
        System.out.println(acessosMemoria);
        }
        System.out.println(acessosMemoria);
        
        
        
        tela.setTextSaida("AX = "+ax);
    }
    
    //        Path pathArquivo = Paths.get(sArquivo);
//        BufferedReader ler = new BufferedReader(new FileReader(pathArquivo.toFile()));
//        LineNumberReader lnr = new LineNumberReader(new FileReader(pathArquivo.toFile()));
//        lnr.skip(Long.MAX_VALUE);
//        int f = lnr.getLineNumber()+1;
//        if (Files.exists(pathArquivo)){

    public static void codificador(short mem[], /*String sArquivo,*/ short shPosicao, ArrayList<String> arrayInstrucoes) throws FileNotFoundException, IOException {
        String sConteudo = "";
        short iMem = shPosicao;
        int iPosConteudo = 0;
        
        for(String sInstrucao : arrayInstrucoes) {
            if (sInstrucao.trim().equals("")) {
                iMem++;
                continue;
            }
            
            //System.out.println(iMem);
            if(iMem + 2 >= mem.length){ AvisoLimiteArray(); break;}

            //String sLinha = ler.readLine();
            if(sInstrucao == null){
                continue;
            }
            sConteudo = sInstrucao.replaceAll(" ", "").trim();

            int ri = 0;
            if (sConteudo.equals("initax")){// "init ax"
                mem[iMem++] = 0;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("moveax,bx")){// "move ax,bx"
                mem[iMem++] = 1;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("moveax,cx")){// "move ax,cx",
                mem[iMem++] = 2;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("movebx,ax")){// "move bx,ax"
                mem[iMem++] = 3;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("movecx,ax")){// "move cx,ax"
                mem[iMem++] = 4;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("moveax,[") 
                    && !sConteudo.contains("moveax,[b")){// "move ax,[",
                mem[iMem++] = 5;
                iPosConteudo = 8;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.contains("moveax,[bx+")){
                mem[iMem++] = 6;
                iPosConteudo = 10;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.contains("moveax,[bp-")){
                mem[iMem++] = 7;
                iPosConteudo = 11;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.contains("moveax,[bp+")){
                mem[iMem++] = 8;
                iPosConteudo = 10;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;

                //acessosMemoria++;
            }
            else if(sConteudo.contains("move[") && sConteudo.contains("],ax") 
                    && !sConteudo.contains("bx") && !sConteudo.contains("bp")){
                mem[iMem++] = 9;
                iPosConteudo = 5;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;

                //acessosMemoria++;
            } else if(sConteudo.contains("move[") && sConteudo.contains("],bx") 
                    && !sConteudo.contains("ax") && !sConteudo.contains("bp")){
                mem[iMem++] = 54;
                iPosConteudo = 5;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;

                //acessosMemoria++;
            }
            else if(sConteudo.contains("move[bx+") && sConteudo.contains(",ax")){
                mem[iMem++] = 10;
                iPosConteudo = 8;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.equals("movebp,sp")){ //"move bp,sp"
                mem[iMem++] = 11;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("movesp,bp")){ //"move sp,bp"
                mem[iMem++] = 12;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("addax,bx")){ //"add ax,bx"
                mem[iMem++] = 13;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("addax,cx")){ //"add ax,cx"
                mem[iMem++] = 14;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("addbx,cx")){ //"add bx,cx"
                mem[iMem++] = 15;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("subax,bx")){ //"sub ax,bx"
                mem[iMem++] = 16;
                //acessosMemoria++;
            }             
            else if(sConteudo.equals("subax,cx")){ //"sub ax,cx"
                mem[iMem++] = 17;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("subbx,cx")){ //"sub bx,cx"
                mem[iMem++] = 18;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("incax")){ //"inc ax"
                mem[iMem++] = 19;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("incbx")){ //"inc bx"
                mem[iMem++] = 20;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("inccx")){ //"inc cx"
                mem[iMem++] = 21;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("decax")){ //"dec ax"
                mem[iMem++] = 22;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("decbx")){ //"dec bx"
                mem[iMem++] = 23;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("deccx")){ //"dec cx"
                mem[iMem++] = 24;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("testax0,")){
                mem[iMem++] = 25;
                iPosConteudo = 8;
                int iPosFinal = sConteudo.length();
                String sAux = "";
                while(iPosConteudo < iPosFinal){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("jmp")){
                mem[iMem++] = 26;
                iPosConteudo = 3;
                int iPosFinal = sConteudo.length();
                String sAux = "";
                while(iPosConteudo < iPosFinal){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("call")){
                mem[iMem++] = 27;
                iPosConteudo = 4;
                int iPosFinal = sConteudo.length();
                String sAux = "";
                while(iPosConteudo < iPosFinal){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("ret")){ //"ret"
                mem[iMem++] = 28;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("inax")){ //"in ax"
                mem[iMem++] = 29;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("outax")){ //"out ax"
                mem[iMem++] = 30;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("pushax")){ //"push ax"
                mem[iMem++] = 31;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("pushbx")){ //"push bx"
                mem[iMem++] = 32;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("pushcx")){ //"push cx"
                mem[iMem++] = 33;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("pushbp")){ //"push bp"
                mem[iMem++] = 34;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("popbp")){ //"pop bp"
                mem[iMem++] = 35;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("popcx")){ //"pop cx"
                mem[iMem++] = 36;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("popbx")){ //"pop bx"
                mem[iMem++] = 37;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("popax")){ //"pop ax"
                mem[iMem++] = 38;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("nop")){ //"nop"
                mem[iMem++] = 39;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("halt")){ //"halt"
                mem[iMem] = 40;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("decsp")){ //"dec sp"
                mem[iMem++] = 41;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("move[bp-") && sConteudo.contains(",ax")){ //"move [bp-"
                mem[iMem++] = 42;
                iPosConteudo = 8;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                        sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.contains("move[bp+") && sConteudo.contains(",ax")){
                mem[iMem++] = 43;
                iPosConteudo = 8;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != ']'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.contains("moveax,{")){
                mem[iMem++] = 44;
                iPosConteudo = 8;
                String sAux = "";
                while(sConteudo.charAt(iPosConteudo) != '}'){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = Short.parseShort(sAux);
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("testaxEqbx,")){
                mem[iMem++] = 45;
                iPosConteudo = 11;
                int iPosFinal = sConteudo.length();
                String sAux = "";
                while(iPosConteudo < iPosFinal){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = (short) (Short.parseShort(sAux) + shPosicao);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("incsp")){ //"inc sp"
                mem[iMem++] = 46;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("moveax,sp")){ //"move ax,sp"
                mem[iMem++] = 47;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("movesp,ax")){ //"move sp,ax"
                mem[iMem++] = 48;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("moveax,bp")){ //"move ax,bp"
                mem[iMem++] = 49;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("movebp,ax")){ //"move bp,ax"
                mem[iMem++] = 50;
                //acessosMemoria++;
            }
            else if(sConteudo.equals("iret")){ //"iret"
                mem[iMem++] = 51;
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
                //acessosMemoria++;
                //acessosMemoria++;
                //acessosMemoria++;
            }
            else if(sConteudo.contains("int")){
                mem[iMem++] = 52;
                iPosConteudo = 3;
                int iPosfinal = sConteudo.length();
                String sAux = "";
                while(iPosConteudo < iPosfinal){
                    sAux += sConteudo.charAt(iPosConteudo++);
                }
                mem[iMem] = Short.parseShort(sAux);
                // 2 acessos
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
                //acessosMemoria++;
                //acessosMemoria++;
                //acessosMemoria++;
                //acessosMemoria++;
                
                //acessosMemoria++;
            }
            else if(sConteudo.equals("subbx,ax")){
                mem[iMem++] = 53;
                //acessosMemoria++;
            } else if (sConteudo.matches("[0-9]+")){
                mem[iMem++] = Short.valueOf(sConteudo);      
            }
            else{
                JOptionPane.showMessageDialog(null, "Instrução inválida, será finalizada a ação.");
                tela.setEdtLinhaExecucao("Instrução inválida.");
                tela.setTextLog("Encontrou uma instrução inválida e parou.\n  ---> "+sConteudo);
                tela.setTextSaida("Ax = "+ax);
                tela.bProgramaInvalido = true;
                break;
            }
        }
    }
    
    private static void AvisoLimiteArray(){
        MainMVM main = new MainMVM();
        main.bLimiteArray = true;
        JOptionPane.showMessageDialog(null, "Limite do array atingido, será finalizada a ação.");
    }
}
