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

@WebServlet(name = "EditarProduto", urlPatterns = {"/EditarProduto"})
public class EditarProduto extends HttpServlet {

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
            String id = request.getParameter("id");
            
            Dao dao = new Dao();
                
            if(dao.connect())
            {
                try
                {
                       
                    dao.createPreparedStatement("select nome,categoria,preco,descricao,foto from produto where id=?");
                    dao.setString(1, id);
                    ResultSet rs = dao.executeQuery();
                        
                    if(rs.next())
                    {
                        String nome = rs.getString("nome");
                        String categoria = rs.getString("categoria");
                        String preco = rs.getString("preco");
                        String descricao = rs.getString("descricao");
                        String foto = rs.getString("foto");
                            
                        request.getServletContext().setAttribute("id", id);
                        request.getServletContext().setAttribute("nome", nome);
                        request.getServletContext().setAttribute("categoria", categoria);
                        request.getServletContext().setAttribute("preco", preco);
                        request.getServletContext().setAttribute("descricao", descricao);
                        request.getServletContext().setAttribute("foto", foto);
                            
                        response.sendRedirect("editar_produto.jsp");
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
