# Projeto PDV

## Instruções de Configuração
### Configuração do Banco de Dados
#### H2 Database
Por padrão, o banco de dados utilizado é o H2. Ele pode ser acessado via URL:
```bash
http://localhost:8080/h2-console
```
Após acessar a URL, clique em `Conectar`. Se as informações de acesso não estiverem preenchidas automaticamente, verifique o arquivo `application-test.properties` na aplicação back-end para obter as credenciais corretas.

#### PostgreSQL
Caso prefira utilizar o PostgreSQL, altere as configurações no arquivo application.properties de `test` para `dev`. As informações do banco de dados estão disponíveis no arquivo application-dev.properties.
Certifique-se de que a propriedade `spring.jpa.hibernate.ddl-auto` esteja configurada como create na primeira execução da aplicação para que o banco de dados seja criado corretamente:
```bash
spring.jpa.hibernate.ddl-auto=create
```
Após a primeira execução, altere esta propriedade para update para manter os dados existentes:
```bash
spring.jpa.hibernate.ddl-auto=update
```

#### População do Banco de Dados
A base de dados será populada automaticamente com os dados do arquivo import.sql na aplicação back-end. Este arquivo contém os dados iniciais necessários para a aplicação.

### Acessando o Sistema
- Login: alex@gmail.com
- Senha: 123456

### Documentação da API
A documentação da API pode ser acessada através do Swagger:
```bash
http://localhost:8080/swagger-ui/index.html
```

### Testando Endpoints
Para testar os endpoints, será necessário obter um token de acesso. Para isso, faça login via Postman utilizando a coleção abaixo:

```bash
https://api.postman.com/collections/8809612-a39be811-7b52-4826-b897-0a9100455c0e?access_key=PMAT-01HZWEJ7YHFKPBS4EMTZTE1PGA
```
#### Importando a Coleção do Postman:

* Acesse o link da coleção do Postman fornecido acima.
* Clique em "Fork" ou "Import" para adicionar a coleção ao seu Postman.
* Utilize a coleção importada para testar os endpoints da API.

### Clonando o Projeto

Para fazer o download ou clonar o projeto, use os seguintes comandos:

```bash
# Clonar o repositório
git clone https://github.com/henriqueborsoilopes/pdv-sistema-unipar2
```

### Instruções Adicionais
* Inicie a aplicação: Certifique-se de que o servidor esteja em execução antes de tentar acessar o sistema ou o banco de dados.

* Ambientes de Desenvolvimento e Teste: Configure os ambientes conforme necessário, garantindo que todas as dependências e serviços estejam funcionando corretamente.

* Suporte: Se precisar de ajuda, entre em contato com o administrador do sistema ou consulte a documentação interna
