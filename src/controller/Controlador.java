package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Controlador {
	
	public Controlador() {
		super();
	}
	
	public String SO() {
		String SO = System.getProperty("os.name");
		return SO;
	}
	
	
	
	public void mostraProcesso(String SO) {
		
		String processo = "";
		if(SO.contains("Windows")) {
			processo = "TASKLIST /FO TABLE";
		}else {
			processo = "ps -ef";
		}
		
		try {
			Process p = Runtime.getRuntime().exec(processo);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	public void mataProcesso(String SO) {
		
		String cmdPid = "";
		String cmdNome = "";
		
		if(SO.contains("Windows")) {
			cmdPid = "TASKKILL /PID";
			cmdNome = "TASKKILL /IM";
		}else {
			cmdPid = "kill";
			cmdNome = "kill";
		}
		
		String processo = JOptionPane.showInputDialog("Digite o P id do processo que deseja encerrar, ou o nome do processo");
		
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		//NumberFormatException -> Exception
		try {
			//TASKKILL /PID XXXX
			pid = Integer.parseInt(processo);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
		} catch (NumberFormatException e) {
			//TASKILL /IM nomeddoprocesso.exe
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(processo);
			
		}
		
		callProcess(buffer.toString());
	}
	
		
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
//			System.err.println(msgErro); --> sys err printa msg no console em VERMELHO
			if (msgErro.contains("740")) {
				//cms /c caminho_do_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}else {
				e.printStackTrace();
			}
		}
	}
}
