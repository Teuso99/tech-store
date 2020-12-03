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
                
        <title>Pagamento</title>
    </head>

    <body>
        
        <%
            String id = request.getParameter("id");
            request.getServletContext().setAttribute("id", id);
        %>
        
        
        <!-- Navbar -->
        <div id="navbar">

        </div>
        
        <div class="container" style="margin-top:5%">
            <div class="py-5 text-center">
                <h2>Pagamento</h2>
                <p class="lead">Insira as informações de pagamento para realizar sua compra.</p>
            </div>
          
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Informações do cartão</h4>
                <form method="POST" action="RealizarPedido">

                    <div class="mb-3">
                        <label for="cartao">Número do cartão</label>
                        <input type="number" class="form-control" id="cartao" name="cartao" required>
                    </div>
          
                    <div class="mb-3">
                        <label for="nome">Nome do titular</label>
                        <input type="text" class="form-control" id="nome" name="nome" required>
                    </div>
          
                    <div class="row">
                        
                        <div class="col-md-6 mb-3">
                            <label for="validade">Validade</label>
                            <input type="text" class="form-control" id="validade" name="validade" required>
                        </div>
                    
                        <div class="col-md-6 mb-3">
                            <label for="cpf">CVV</label>
                            <input type="number" class="form-control" id="cvv" name="cvv" required>
                        </div>
                    </div>
                
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Concluir</button>
                </form>
            </div>

        </div>
    </body>
</html>
