package cadastros;

import dao.Dao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UpdateProduto", urlPatterns = {"/UpdateProduto"})
@MultipartConfig
public class UpdateProduto extends HttpServlet {

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
            //if part != null
            
            Dao dao = new Dao();
            
            if(dao.connect())
            {
                try
                {
                    Part filePart = request.getPart("foto");
                    
                    if(filePart.getSize() != 0)
                    {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


                        File uploads = new File("/Users/mateu/Documents/NetBeansProjects/tech-store/web/resources/img");
                        File file = File.createTempFile("produto-",".jpg", uploads);

                        Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        
                        dao.createPreparedStatement("update produto set nome = ?,categoria = ?,preco = ?,descricao = ?, foto = ? where id = ?");
                    
                        dao.setString(1, request.getParameter("nome"));
                        dao.setString(2, request.getParameter("categoria"));
                        dao.setString(3, request.getParameter("preco"));
                        dao.setString(4, request.getParameter("descricao"));
                        dao.setString(5, "resources/img/"+file.getName());
                        dao.setString(6, request.getServletContext().getAttribute("id").toString());

                        dao.execute();
                        dao.close();
                    }
                    else
                    {
                        dao.createPreparedStatement("update produto set nome = ?,categoria = ?,preco = ?,descricao = ? where id = ?");
                    
                        dao.setString(1, request.getParameter("nome"));
                        dao.setString(2, request.getParameter("categoria"));
                        dao.setString(3, request.getParameter("preco"));
                        dao.setString(4, request.getParameter("descricao"));
                        dao.setString(5, request.getServletContext().getAttribute("id").toString());

                        dao.execute();
                        dao.close();
                    }
                    
                    
                    request.getServletContext().removeAttribute("id");
                    request.getServletContext().removeAttribute("nome");
                    request.getServletContext().removeAttribute("categoria");
                    request.getServletContext().removeAttribute("preco");
                    request.getServletContext().removeAttribute("descricao");
                    request.getServletContext().removeAttribute("foto");
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Produto atualizado com sucesso!');");
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
                out.println("alert('Erro na hora de atualizar o produto.');");
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
