package com.senac.petchopp.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class Upload {

	private static String windowsPadrao = "C:\\";

	private static String localDoProjeto = System.getProperty("user.dir");

	private static Path caminhoDoProjeto = Paths.get(localDoProjeto);

	public static void salvar(MultipartFile arquivo) throws IOException {
		byte[] bytesArquivo;
		try {
			bytesArquivo = arquivo.getBytes();
			caminhoDoProjeto = Paths.get(createWay(arquivo.getOriginalFilename()));
			Files.write(caminhoDoProjeto, bytesArquivo);
		} catch (IOException e) {
			throw new IOException("Falha ao fazer o upload do arquivo.", e);
		}
	}

	public static String createWay(String nomeDoArquivo) {
		// texto seria o nome do arquivo
		// texto seria o nome do arquivo
		if (caminhoDoProjeto.toString().contains(windowsPadrao)) {
			// Caso Windows
			caminhoDoProjeto = Paths
					.get(caminhoDoProjeto + "\\src\\main\\resources\\static\\uploads\\" + nomeDoArquivo);
		} else {
			// Caso Linux
			caminhoDoProjeto = Paths.get(caminhoDoProjeto + "/src/main/resources/static/uploads/" + nomeDoArquivo);
		}
		return caminhoDoProjeto.toString();
	}

}
