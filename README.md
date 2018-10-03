# Como rodar

## Requisitos
 - jdk 1.8
 - jre 1.8

##### Run
* No Eclipse: Basicamente se não der erro nenhum é só usar o Run as Java Application e selecionar a classe principal (PetchoppApplication.java).
* No Netbeans aparentemente ele cria sozinho a pasta webcontent e coloca o context path lá, mas funcionou tranquilamente, só demora um pouco para rodar.
    
 
##### Error
1. No compiler is provided in this environment (Ocorre ao executar o projeto no Eclipse)
 
##### Solução
1. 
    * Nas configurações do **Eclipse**(não do projeto) adicione ou altere as configurações em Java>Installed JREs
    ![eclipse config image](https://i.imgur.com/3mMAHMX.png)
    * Então nas configurações do **projeto** em Java Build Path
    ![project config image1](https://i.imgur.com/5YKZSXh.png)
    * E verifique seu Java Compiler(só pra ver se bate)
    ![project config image2](https://i.imgur.com/jeK6TEs.png)