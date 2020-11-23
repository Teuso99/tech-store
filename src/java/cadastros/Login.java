/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author mateu
 */
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
                        dao.createPreparedStatement("select 1 from cliente where email=? and senha=?");
                        dao.setString(1, request.getParameter("email"));
                        dao.setString(2, request.getParameter("senha"));
                        ResultSet rs = dao.executeQuery();
                        
                        if(rs.next())
                        {
                            String tipo = "cliente";
                            request.getSession().setAttribute("email", request.getParameter("email"));
                            request.getSession().setAttribute("tipo", tipo);
                            out.println("Logado como cliente");
                            
                            response.sendRedirect("index.html");
                        }
                        else
                        {
                            out.println("Usuário/senha inválido");
                        }
                        
                        rs.close();
                        dao.close();
                    }
                    else
                    {
                        dao.createPreparedStatement("select 1 from funcionario where email=? and senha=?");
                        dao.setString(1, request.getParameter("email"));
                        dao.setString(2, request.getParameter("senha"));
                        ResultSet rs = dao.executeQuery();
                        
                        if(rs.next())
                        {
                            String tipo = "funcionario";
                            request.getSession().setAttribute("email", request.getParameter("email"));
                            request.getSession().setAttribute("tipo", tipo);
                            out.println("Logado como funcionario");
                            
                            response.sendRedirect("index.html");
                        }
                        else
                        {
                            out.println("Usuário/senha inválido");
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
