##Trabalho 02 - Sistemas distribuídos

Tecnologias utilizadas:

- Java EE 7
- Angular 4
- WildFly 12*



Para buildar o serviço de validação, navegue até a pasta `backend` e execute o comando:

`mvn clean package`

O artefato gerado, `Trabalho02.war`, fica na pasta `target`. Com esse artefato, fazer o deploy para inicializar o serviço.

Para o frontend, navegue até a pasta `frontend\angular-app` e execute os seguintes comandos:

- `npm install`
- `ng serve`

A aplicação, por padrão, é servida na porta `4200`.



<sup> *Testado no WildFly, porém, pode ser utilizado outro servidor de aplicação compatível <sup>