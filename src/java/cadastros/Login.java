package cadastros;

import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
                    String[] checked = request.getParameterValues("funcionario");
                    
                    if(checked == null)
                    {
                        dao.createPreparedStatement("select id,nome from cliente where email=? and senha=?");
                        dao.setString(1, request.getParameter("email"));
                        dao.setString(2, request.getParameter("senha"));
                        ResultSet rs = dao.executeQuery();
                        
                        if(rs.next())
                        {
                            String tipo = "cliente";
                            String nome = rs.getString("nome");
                            String id = rs.getString("id");
                            request.getSession().setAttribute("email", request.getParameter("email"));
                            request.getSession().setAttribute("tipo", tipo);
                            request.getSession().setAttribute("nome", nome);
                            request.getSession().setAttribute("id",id);
                            
                            response.sendRedirect("index.html");
                        }
                        else
                        {
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Usuário/senha inválido');");
                            out.println("location='index.html';");
                            out.println("</script>");
                        }
                        
                        rs.close();
                        dao.close();
                    }
                    else
                    {
                        dao.createPreparedStatement("select id,nome from funcionario where email=? and senha=?");
                        dao.setString(1, request.getParameter("email"));
                        dao.setString(2, request.getParameter("senha"));
                        ResultSet rs = dao.executeQuery();
                        
                        if(rs.next())
                        {
                            String tipo = "funcionario";
                            String id = rs.getString("id");
                            request.getSession().setAttribute("email", request.getParameter("email"));
                            request.getSession().setAttribute("tipo", tipo);
                            request.getSession().setAttribute("nome", rs.getString("nome"));
                            request.getSession().setAttribute("id",id);
                            
                            response.sendRedirect("admin.html");
                        }
                        else
                        {
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Usuário/senha inválido ou você não é um funcionário');");
                            out.println("location='index.html';");
                            out.println("</script>");
                        }
                        
                        rs.close();
                        dao.close();
                    }
                }
                catch(Exception e)
                {
                    
                }
            }
            else
            {
                out.print("Erro: "+dao.getErro());
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
