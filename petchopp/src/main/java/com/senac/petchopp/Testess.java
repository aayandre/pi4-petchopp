package com.senac.petchopp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Testess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// C:\Users\aayan\Documents\Programming\petchoppLiso\pi4-petchopp\petchopp\src\main\resources\stat

		String windowsPadrao = "C:\\";
		String localDoProjeto = System.getProperty("user.dir");
		System.out.println("Local do projeto: " + localDoProjeto);
		Path caminhoDoProjeto = Paths.get(localDoProjeto);

		// texto seria o nome do arquivo
		if (caminhoDoProjeto.toString().contains(windowsPadrao)) {
			// Caso Windows
			caminhoDoProjeto = Paths.get(caminhoDoProjeto + "\\src\\main\\resources\\static\\uploads\\");
		} else {
			// Caso Linux
			caminhoDoProjeto = Paths.get(caminhoDoProjeto + "/src/main/resources/static/uploads/");
		}
		System.out.println("Caminho do upload completo: " + caminhoDoProjeto.toString());
	}

}
