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
                
        <title>Lista de Cliente</title>
    </head>
    <body>
        
        <!-- Navbar -->
        <div id="navbar">

        </div>
        
        <div class="py-5 text-center" style="margin-top:5%">
                <h2>Lista de Clientes</h2>
                <p class="lead">Abaixo se encontra a lista de todos os clientes cadastrados no sistema.</p>
        </div>
        
        <main class="container align-middle">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                        <th scope="col">CPF</th>
                        <th scope="col">E-mail</th>
                    </tr>
                </thead>
                    <%
                        Dao dao = new Dao();

                            if(dao.connect())
                            {
                               try
                               {
                                    dao.createPreparedStatement("select nome,cpf,email from cliente");
                                    ResultSet rs = dao.executeQuery();
                                    
                                    int i = 1;

                                    while(rs.next())
                                    {
                                        String nome = rs.getString("nome");
                                        String cpf = rs.getString("cpf");
                                        String email = rs.getString("email");
                    %>

                    <tr>
                        <th scope="row"><% out.print(i); %></th>
                        <td><% out.print(nome); %></td>
                        <td><% out.print(cpf); %></td>
                        <td><% out.print(email); %></td>
                    </tr>
                    <%
                                        i++;
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
