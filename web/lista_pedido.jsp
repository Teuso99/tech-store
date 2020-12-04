<%@page import="java.sql.ResultSet"%>
<%@page import="dao.Dao"%>
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
                
        <title>Lista de Produtos</title>
    </head>
    <body>
        
        <!-- Navbar -->
        <div id="navbar">

        </div>
        
        <div class="py-5 text-center" style="margin-top:5%">
                <h2>Lista de Produtos</h2>
                <p class="lead">Abaixo se encontra a lista de todos os pedidos realizados por clientes cadastrados no sistema.</p>
        </div>
        
        <main class="container align-middle">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Cliente</th>
                        <th scope="col">Produto</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Data</th>
                    </tr>
                </thead>
                    <%
                        Dao dao = new Dao();

                            if(dao.connect())
                            {
                               try
                               {
                                    dao.createPreparedStatement("select cliente.nome,produto.nome,pedido.id,pedido.quantidade,pedido.datapedido from cliente,produto,pedido where cliente.id = pedido.idcliente and produto.id = pedido.idproduto");
                                    ResultSet rs = dao.executeQuery();

                                    while(rs.next())
                                    {
                                        String nomeCliente = rs.getString("cliente.nome");
                                        String nomeProduto = rs.getString("produto.nome");
                                        String id = rs.getString("pedido.id");
                                        String qtd = rs.getString("quantidade");
                                        String data = rs.getString("datapedido");
                    %>

                    <tr>
                        <th scope="row"><% out.print(id); %></th>
                        <td><% out.print(nomeCliente); %></td>
                        <td><% out.print(nomeProduto); %></td>
                        <td><% out.print(qtd); %></td>
                        <td><% out.print(data); %></td>
                    </tr>
                    <%
                                    };

                                    rs.close();
                                    dao.close();
                               }
                               catch(Exception e)
                               {
                                   out.print("Erro: "+e);
                               }


                            }
                            else
                            {
                                out.print("Erro: "+dao.getErro());
                            }
                    %>
                
            </table>
        </main>
    </body>
</html>
