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

@WebServlet(name = "EditarCliente", urlPatterns = {"/EditarCliente"})
public class EditarCliente extends HttpServlet {

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
            String tipo = (String)request.getSession().getAttribute("tipo");
            
            if(tipo.equals("cliente"))
            {
                Dao dao = new Dao();
                
                if(dao.connect())
                {
                   try
                   {
                        String email = (String)request.getSession().getAttribute("email");
                       
                        dao.createPreparedStatement("select id,endereco,cpf,cep,idade,senha from cliente where email=?");
                        dao.setString(1, email);
                        ResultSet rs = dao.executeQuery();
                        
                        if(rs.next())
                        {
                            int id = rs.getInt("id");
                            String endereco = rs.getString("endereco");
                            String cpf = rs.getString("cpf");
                            String cep = rs.getString("cep");
                            int idade = rs.getInt("idade");
                            String senha = rs.getString("senha");
                            
                            request.getSession().setAttribute("id", id);
                            request.getSession().setAttribute("endereco", endereco);
                            request.getSession().setAttribute("cpf", cpf);
                            request.getSession().setAttribute("cep", cep);
                            request.getSession().setAttribute("idade", idade);
                            request.getSession().setAttribute("senha", senha);
                            
                            response.sendRedirect("editar_cliente.jsp");
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
                    out.print("Erro: "+dao.getErro());
                }
                
            }
            else
            {
                out.println(request.getSession().getAttribute("tipo"));
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
