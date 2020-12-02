<%@page import="java.sql.ResultSet"%>
<%@page import="dao.Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <p class="lead">Abaixo se encontra a lista de todos os produtos cadastrados no sistema.</p>
        </div>
        
        <main class="container align-middle">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Foto</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                    <%
                        Dao dao = new Dao();

                            if(dao.connect())
                            {
                               try
                               {
                                    dao.createPreparedStatement("select id,nome,foto,preco,categoria from produto");
                                    ResultSet rs = dao.executeQuery();

                                    while(rs.next())
                                    {
                                        int id = rs.getInt("id");
                                        String nome = rs.getString("nome");
                                        String foto = rs.getString("foto");
                                        String preco = rs.getString("preco");
                                        String categoria = rs.getString("categoria");
                    %>

                    <tr>
                        <th scope="row"><% out.print(id); %></th>
                        <td>
                            <img src="<%=foto%>" class="mr-3" alt="..." style="width: 64px; height: 64px">  
                        </td>
                        <td><% out.print(categoria); %></td>
                        <td><% out.print(nome); %></td>
                        <td>R$ <% out.print(preco); %></td>
                        <td><a class="btn btn-primary" role="button" href="EditarProduto?id=<%=id%>">Editar</a> <a class="btn btn-danger" role="button" href="ExcluirProduto?id=<%=id%>">Excluir</a></td>
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
