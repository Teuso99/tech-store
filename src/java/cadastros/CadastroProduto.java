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

@WebServlet(name = "CadastroProduto", urlPatterns = {"/CadastroProduto"})
@MultipartConfig
public class CadastroProduto extends HttpServlet {

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
                    try
                    {
                        
                    }
                    catch(Exception e)
                    {
                        out.print("Erro: " + e);
                    }
                    //Converter o preço para o campo decimal no bd
                        String preco = request.getParameter("preco");
                        preco = preco.replace(",",".");

                        //Trabalhando com a foto
                        Part filePart = request.getPart("foto");
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


                        File uploads = new File("/Users/mateu/Documents/NetBeansProjects/tech-store/web/resources/img");
                        File file = File.createTempFile("produto-",".jpg", uploads);

                        Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                    
                    dao.createPreparedStatement("insert into produto(nome,categoria,preco,descricao,foto)values(?,?,?,?,?)");
                    
                    dao.setString(1, request.getParameter("nome"));
                    dao.setString(2, request.getParameter("categoria"));
                    dao.setString(3, preco);
                    dao.setString(4, request.getParameter("descricao"));
                    dao.setString(5, "resources/img/"+file.getName());
                    
                    dao.execute();
                    dao.close();
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Produto cadastrado com sucesso!');");
                    out.println("location='admin.html';");
                    out.println("</script>");
                }
                catch(Exception e)
                {
                    out.println("Erro: " + e);
                }
            }
            else
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Erro no cadastro do produto.');");
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
