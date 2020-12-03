window.onload = function()
{
    document.getElementById("adicionarProduto").onclick = function ()
    {
        location.href = "cadastrar_produto.html";
    };

    document.getElementById("adicionarFuncionario").onclick = function ()
    {
        location.href = "cadastrar_funcionario.html";
    };

    document.getElementById("listaCliente").onclick = function ()
    {
        location.href = "lista_cliente.jsp";
    };

    document.getElementById("listaFuncionario").onclick = function ()
    {
        location.href = "lista_funcionario.jsp";
    };

    document.getElementById("listaProduto").onclick = function ()
    {
        location.href = "lista_produto.jsp";
    };

    document.getElementById("listaPedido").onclick = function ()
    {
        location.href = "lista_pedido.jsp";
    };
};
