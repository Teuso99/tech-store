$.ajax("navbar.jsp").done(function (data)
{
    $('#navbar').html(data);    
})

$.ajax("home.jsp").done(function (data)
{
    $('#home').html(data);    
})

window.onload = function()
{ 
    document.getElementById("btnLogin").onclick = function ()
    {
        location.href = "login.html";
    }; 
};