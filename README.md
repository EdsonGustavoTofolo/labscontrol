# LabsControl
# Configurando
Utilizado o vídeo para demonstração [Getting Started with Payara Server](https://www.youtube.com/watch?v=tl8UbAETM68) <br/>
Utilizado link para configuracao do mysql no payara [Using MySQL with Payara](https://blog.payara.fish/using-mysql-with-payara) <br/>
1. Baixar o payara 5.2022.2 através do link [Payara 5.2022.2](https://www.payara.fish/downloads/payara-platform-community-edition/)
2. Fazer a extração e acessar a pasta /bin
3. Abrir o terminal e rodar o comando `./asadmin start-domain`
4. Baixar o mysql-connector-java-8.0.29.jar, o qual pode ser conseguido através no diretório do repositório local do maven geralmente localizado em ~/.m2/repository/mysql/mysql-connector-java/8.0.29
5. Ir para a pasta /bin do payara server
6. Rodar o comando `./asadmin add-library mysql-connector-java-8.0.29.jar`
7. Reiniciar o payara server através do comando `./asadmin restart-domain`
8. Abrir o browser e acessar http://localhost:4848
9. Criar um pool em JDBC Connection Pools da seguinte forma:
   1. Pool Name: labscontrolpool
   2. Resource Type: javax.sql.DataSource
   3. Database Driver Vendor: MySql8
   4. Press Next
   5. Em Additional Properties, selecione todos e remova
   6. Adicione as seguintes propriedades e valores:
      1. MySQL rodando na máquina local
         1. User: informe o usuario
         2. Password: informe a senha
         3. PortNumber: 3306
         4. ServerName: localhost
         5. Url: jdbc:mysql://:3306/labscontrol
         6. URL: jdbc:mysql://:3306/labscontrol
         7. UseSSL: false
         8. DatabaseName: labscontrol
         9. Port: 3306
      2. MySQL rodando via Docker
         1. User: informe o usuario
         2. Password: informe a senha
         3. PortNumber: porta disponivel via Docker
         4. Url: jdbc:mysql://usuario:senha@localhost:<porta disponivel via docker>/labscontrol
         5. URL: jdbc:mysql://usuario:senha@localhost:<porta disponivel via docker>/labscontrol
         6. UseSSL: false
         7. DatabaseName: labscontrol
         8. Port: porta disponivel via Docker
         9. allowPublicKeyRetrieval: true
   7. Salve
10. Criar um JDBC Resources da seguinte forma:
    1. JNDI Name: jdbc/labscontrol
    2. Pool Name: labscontrolpool (este é o pool criado no passo 9)
    3. Pressione Ok
11. Deploy da aplicacao
    1. Em Applications clicar em Deploy
    2. Em Location selecionar o arquivo labscontrol.war gerado 
    3. Clicar em Ok
    4. Voltar em Applications e na registro que acabou de ser inserido clicar em Launch

Item 9.1 ![Edicao da JDBC Connection Pool](img/jdbc-connection-pool.png)
Item 9.6 ![Edicao da JDBC Connection Pool - Additional Properties](img/jdbc-connection-pool-additional-properties.png)
Item 10 ![Edicao da JDBC Resource](img/jdbc-resource.png)
