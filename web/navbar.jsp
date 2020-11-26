<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #userMenu
            {
                min-width: 20px !important;
            }
        </style>

        <script>
            window.onload = function()
            { 
                document.getElementById("btnLogin").onclick = function ()
                {
                    location.href = "login.html";
                }; 
            };
        </script>
        <title></title>
    </head>
    <body>
        <%
            String usuario=(String)session.getAttribute("nome");
            String tipo=(String)session.getAttribute("tipo");
        %>
        
        <nav class="navbar navbar-expand-md navbar-dark fixed-top">
            <a class="navbar-brand" href="index.html">Tech Store</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-light" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Produtos</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown01">
                            <a class="dropdown-item" href="#">Produto1</a>
                            <a class="dropdown-item" href="#">Produto2</a>
                            <a class="dropdown-item" href="#">Produto3</a>
                        </div>
                    </li>
                </ul>
                
                <form class="form-inline mt-2 mt-md-0 mr-auto">
                    <input class="form-control mr-sm-2" type="text" placeholder="Procurar..." aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Procurar</button>
                </form>

                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link text-light" href="#">Carrinho</a>
                    </li>
                    
                    <c:choose>
                        <c:when test = "${tipo == 'cliente'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-light" href="#" id="dropdown01" 
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><% out.print(usuario); %></a>
                                <div class="dropdown-menu" aria-labelledby="dropdown01" id="userMenu">
                                    <a class="dropdown-item" href="Logout">Logout</a>
                                </div>
                            </li>
                        </c:when>
                        
                        <c:when test = "${tipo == 'funcionario'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-light" href="#" id="dropdown01" 
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><% out.print(usuario); %></a>
                                <div class="dropdown-menu" aria-labelledby="dropdown01" id="userMenu">
                                    <a class="dropdown-item" href="Logout">Logout</a>
                                </div>
                            </li>
                        </c:when>
                        
                        <c:otherwise>
                            <li class="nav-item">
                                <button type="button" class="btn btn-outline-light" id="btnLogin">Login</button>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    
                </ul>
            </div>
        </nav>
    </body>
</html>
