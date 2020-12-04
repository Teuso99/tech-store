<%@page import="java.sql.ResultSet"%>
<%@page import="dao.Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div class="card">
            <h5 class="card-header">Novidades</h5>
            <div class="card-body">
                    
                <%
                Dao dao = new Dao();

                if(dao.connect())
                {
                    try
                    {
                        dao.createPreparedStatement("select id,nome,foto,preco,descricao from produto order by id desc limit 5");
                        ResultSet rs = dao.executeQuery();

                        while(rs.next())
                        {
                            int id = rs.getInt("id");
                            String nome = rs.getString("nome");
                            String foto = rs.getString("foto");
                            String preco = rs.getString("preco");
                            String descricao = rs.getString("descricao");
                %>
                
                    <!-- Produtos -->
                    <div class="media mt-2">
                        <img src="<%=foto%>" class="mr-3" alt="..." style="width: 64px; height: 64px">
                        <div class="media-body">
                            <h5 class="mt-0"><% out.print(nome); %></h5>
                            <p><% out.print(descricao); %></p>
                            <h5>R$ <% out.print(preco); %></h5>
                            <a class="btn btn-primary" role="button" href="carrinho.jsp?id=<%=id%>">Comprar</a>
                        </div>
                    </div>
                    <hr>
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
                    out.print("Não existe produtos correspondentes a essa categoria.");
                }
                %>    
                </div>
        </div>
    </body>
</html>
