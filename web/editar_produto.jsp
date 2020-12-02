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
                
        <title>Editar Funcionário</title>
    </head>

    <body>
        <%
            int id = Integer.parseInt(getServletContext().getAttribute("id").toString());
            String nome = (String)request.getServletContext().getAttribute("nome");
            String categoria = (String)request.getServletContext().getAttribute("categoria");
            String preco = (String)request.getServletContext().getAttribute("preco");
            String descricao = (String)request.getServletContext().getAttribute("descricao");
            String foto = (String)request.getServletContext().getAttribute("foto");
        %>
        
        <!-- Navbar -->
        <div id="navbar">

        </div>
        
        <div class="container">
            <div class="py-5 text-center">
                <h2>Formulário de Cadastro</h2>
                <p class="lead">Insira as informações abaixo para atualizar o produto no sistema.</p>
            </div>
          
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Informações do produto</h4>
                <form method="POST" action="UpdateProduto" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label for="nome">Nome do produto</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="<% out.print(nome); %>" required>
                    </div>
          
                    <div class="mb-3">
                        <label for="descricao">Descrição</label>
                        <textarea class="form-control" id="descricao" name="descricao" rows="3" required><% out.print(descricao); %></textarea>
                    </div>
          
                    <div class="row">
                        
                        <div class="col-md-5 mb-3">
                            <label for="categoria">Categoria</label>
                            <input type="text" class="form-control" id="cetegoria" name="categoria" value="<% out.print(categoria); %>" required>
                        </div>
                        
                        <div class="col-md-4 mb-3">
                            <label for="preco">Preço</label>
                            <input type="text" class="form-control" id="preco" name="preco" value="<% out.print(preco); %>" required>
                        </div>
                        
                        <div class="col-md-3 mb-3">
                            <label for="foto">Foto</label>
                            <input type="file" class="form-control-file" id="foto" name="foto">
                        </div>
                    </div>
                
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Concluir</button>
                </form>
            </div>

        </div>

    </body>
</html>

