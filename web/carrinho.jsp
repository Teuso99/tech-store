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
        <title>Carrinho</title>
    </head>
    <body>
        <%
            if(request.getSession().getAttribute("tipo") == null)
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Apenas usuários cadastrados podem realizar compras.');");
                out.println("location='index.html';");
                out.println("</script>");
            }
            else
            {
        %>
        
        <!-- Navbar -->
        <div id="navbar">

        </div>     
        
        <div class="py-5 text-center" style="margin-top:5%">
                <h2>Carrinho</h2>
                <p class="lead">Confirme a compra a ser realizada.</p>
        </div>
        
        <main class="container align-middle">
            <form method="POST" action="RealizarPedido">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Foto</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Preço Unitário</th>
                        <th scope="col">Comprar</th>
                    </tr>
                </thead>
                
                <%
                        String id = request.getParameter("id");
                        request.getServletContext().setAttribute("idProduto", id);
                    
                        Dao dao = new Dao();

                            if(dao.connect())
                            {
                               try
                               {
                                    dao.createPreparedStatement("select nome,foto,preco from produto where id = ?");
                                    dao.setString(1,id);
                                    ResultSet rs = dao.executeQuery();

                                    if(rs.next())
                                    {
                                        String nome = rs.getString("nome");
                                        String foto = rs.getString("foto");
                                        String preco = rs.getString("preco");
                    %>

                    <tr>
                        <td>
                            <img src="<%=foto%>" class="mr-3" alt="..." style="width: 64px; height: 64px">  
                        </td>
                        <td><% out.print(nome); %></td>
                        <td><input type="number" value="1" name="qtd" required></td>
                        <td>R$ <% out.print(preco); %></td>
                        <td><button class="btn btn-primary" type="submit">Comprar</button></td>
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
            }
                    %>
            </table>
            </form>
    </body>
</html>
