package cadastros;

import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RealizarPedido", urlPatterns = {"/RealizarPedido"})
public class RealizarPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            Dao dao = new Dao();
            String idProduto = request.getServletContext().getAttribute("idProduto").toString();
            String idCliente = request.getSession().getAttribute("id").toString();
            String qtd = request.getParameter("qtd");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            if(dao.connect())
            {
                try
                {
                    dao.createPreparedStatement("insert into pedido(idcliente,idproduto,quantidade,datapedido)values(?,?,?,?)");
                    dao.setString(1, idCliente);
                    dao.setString(2, idProduto);
                    dao.setString(3, qtd);
                    dao.setString(4, timestamp.toString());

                    dao.execute();
                    dao.close();
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Pedido realizado com sucesso!');");
                    out.println("location='index.html';");
                    out.println("</script>");
                }
                catch(Exception e)
                {
                    out.print("Erro: "+e);
                }
            }
            else
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Erro na realização do pedido.');");
                out.println("location='index.html';");
                out.println("</script>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
