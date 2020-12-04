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
        
        <title>Resultados da busca</title>
    </head>
    <body>
        <!-- Navbar -->
        <div id="navbar">

        </div>
        
        <div class="card" style="margin-top:8%;">
            <h5 class="card-header">Resultados da pesquisa</h5>
            <div class="card-body">
                    
                <%
                Dao dao = new Dao();
                
                String busca = "%"+request.getParameter("busca")+"%";
                
                if(dao.connect())
                {
                    try
                    {
                        dao.createPreparedStatement("select id,nome,foto,preco,descricao from produto where nome like ? or categoria like ? order by id desc;");
                        dao.setString(1,busca);
                        dao.setString(2,busca);
                        
                        ResultSet rs = dao.executeQuery();

                        if(!rs.next())
                        {
                            out.println("Não existe produto correspondente a sua pesquisa.");
                        }
                        else
                        {
                            do
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
                            }while(rs.next());
                        }
                        
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
                    out.print("Erro na pesquisa.");
                }
                %>    
                </div>
        </div>
    </body>
</html>
