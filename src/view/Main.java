package view;

import javax.swing.JOptionPane;

import controller.Controlador;

public class Main {

	public static void main(String[] args) {
		
		Controlador Control = new Controlador();
		
		String SO = Control.SO();
		
		int opcao=0;
		
		while (opcao!=9) {	
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite - 1: Verificar processos ativos \nDigite - 2: Para encerar um processo"
																	+ " \nDigite - 9: para encerar"));
		
			switch (opcao) {
			
			case 1:	
				Control.mostraProcesso(SO);
				break;
			case 2:
				Control.mataProcesso(SO);
				break;
				
			case 9:
				JOptionPane.showMessageDialog(null, "Fim");
				break;
			
			default:
				JOptionPane.showMessageDialog(null,"Opção Inválida");
		
		
			}
		
		
		}
	}

}
