$.ajax("navbar.jsp").done(function (data)
{
    $('#navbar').html(data);    
})

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
};