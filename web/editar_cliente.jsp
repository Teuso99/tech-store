<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <!-- Tags meta - charset para utilizar utf-8 e 
                viewport para utilização da responsividade com Bootstrap -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <!-- Referências arquivos CSS -->
        <link href="resources/css/bootstrap.min.css" rel="stylesheet" />
        <link href="resources/css/style.css" rel="stylesheet" />
                
        <!-- Referências arquivos JS -->
        <script src="resources/js/jquery-3.5.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/navbar.js"></script>
                
        <title>Editar Perfil</title>
    </head>

    <body>
        <%
            int id = Integer.parseInt(session.getAttribute("id").toString());
            String nome = (String)session.getAttribute("nome");
            String email = (String)session.getAttribute("email");
            String endereco = (String)session.getAttribute("endereco");
            String cep = (String)session.getAttribute("cep");
            String cpf = (String)session.getAttribute("cpf");
            String senha = (String)session.getAttribute("senha");
            int idade = Integer.parseInt(session.getAttribute("idade").toString());
        %>
        
        <!-- Navbar -->
        <div id="navbar">

        </div>
        
        <div class="container">
            <div class="py-5 text-center">
                <h2>Formulário de Cadastro</h2>
                <p class="lead">Insira suas informações abaixo para se cadastrar no sistema.</p>
            </div>
          
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Informações pessoais</h4>
                <form method="POST" action="UpdateCliente">

                    <div class="mb-3">
                        <label for="nome">Nome completo</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="<%out.print(nome);%>" required>
                    </div>
          
                    <div class="mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%out.print(email);%>"  required>
                    </div>

                    <div class="mb-3">
                        <label for="senha">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" value="<%out.print(senha);%>" required>
                    </div>
          
                    <div class="mb-3">
                        <label for="endereco">Endereço</label>
                        <input type="text" class="form-control" id="endereco" name="endereco" value="<%out.print(endereco);%>">
                    </div>
          
                    <div class="row">
                        
                        <div class="col-md-5 mb-3">
                            <label for="idade">Idade</label>
                            <input type="number" class="form-control" id="idade" name="idade" value="<%out.print(idade);%>" required>
                        </div>
                    
                        <div class="col-md-4 mb-3">
                            <label for="cpf">CPF</label>
                            <input type="number" class="form-control" id="cpf" name="cpf" value="<%out.print(cpf);%>" required>
                        </div>

                        <div class="col-md-3 mb-3">
                            <label for="cep">CEP</label>
                            <input type="number" class="form-control" id="cep" name="cep" value="<%out.print(cep);%>">
                        </div>
                    </div>
                
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Concluir</button>
                </form>
            </div>

        </div>

    </body>
</html>

