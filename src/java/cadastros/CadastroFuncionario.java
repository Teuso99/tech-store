package cadastros;

import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastroFuncionario", urlPatterns = {"/CadastroFuncionario"})
public class CadastroFuncionario extends HttpServlet {

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
            
            if(dao.connect())
            {
                try
                {
                    dao.createPreparedStatement("insert into funcionario(nome,cpf,idade,email,senha)values(?,?,?,?,?)");
                    dao.setString(1, request.getParameter("nome"));
                    dao.setString(2, request.getParameter("cpf"));
                    dao.setString(3, request.getParameter("idade"));
                    dao.setString(4, request.getParameter("email"));
                    dao.setString(5, request.getParameter("senha"));
                    
                    dao.execute();
                    dao.close();
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Funcionário cadastrado com sucesso!');");
                    out.println("location='admin.html';");
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
                out.println("alert('Erro no cadastro do funcionário.');");
                out.println("location='admin.html';");
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
