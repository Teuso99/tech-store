package cadastros;

import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateCliente", urlPatterns = {"/UpdateCliente"})
public class UpdateCliente extends HttpServlet {

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
        try (PrintWriter out = response.getWriter())
        {
            Dao dao = new Dao();
            
            if(dao.connect())
            {
                try
                {
                    dao.createPreparedStatement("update cliente set nome = ?,endereco = ?,cpf = ?,cep = ?,idade = ?,email = ?,senha = ? where id = ?");
                    dao.setString(1, request.getParameter("nome"));
                    dao.setString(2, request.getParameter("endereco"));
                    dao.setString(3, request.getParameter("cpf"));
                    dao.setString(4, request.getParameter("cep"));
                    dao.setString(5, request.getParameter("idade"));
                    dao.setString(6, request.getParameter("email"));
                    dao.setString(7, request.getParameter("senha"));
                    dao.setString(8, request.getSession().getAttribute("id").toString());
                    
                    dao.execute();
                    dao.close();
                    
                    response.sendRedirect("index.html");
                }
                catch(Exception e)
                {
                    out.print("Erro: "+e);
                }
            }
            else
            {
                out.print("Erro no acesso da informação."+dao.getErro());
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
