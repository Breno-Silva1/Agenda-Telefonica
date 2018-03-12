package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contato;
import entidades.Endereco;

@WebServlet(name = "Agenda", urlPatterns = { "/Agenda" })
public class Agenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Contato> agenda;

    public Agenda() {
    }
    
    public void init() throws ServletException {
        agenda = new ArrayList<>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opc = request.getParameter("operacao");
        if (opc.equals("cadastrar")) {
            cadastrar(request, response); 
        } else if (opc.equals("editar")) {
            editar(request, response, request.getParameter("numero_busca")); 
        } else if (opc.equals("consultar")) {
            consultar(request, response, request.getParameter("nome"));
        } else if (opc.equals("excluir")){
            excluir(request, response, request.getParameter("nome"));
        }
	}

	public void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//Contato
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String numero = request.getParameter("numero");
        
        //Endereço
        String rua = request.getParameter("rua");
        String numero_casa = request.getParameter("num");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        
        Endereco endereco = new Endereco(rua, numero_casa, bairro, cidade, estado);
        Contato contato = new Contato(nome, email, numero, endereco);
        
        agenda.add(contato);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Contato Cadastrado</title>");   
        out.println("<link rel=\"stylesheet\" href=\"css/estilo.css\">");
        out.print("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
        out.print("<div class=\"cell\"><div class=\"tela\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div style=\"text-align: left;padding: 10px;\" title=\"Voltar\"><a class=\"back\" href=\"index.html\"><i class=\"fa fa-arrow-left\"></i></a></div>");
        out.println("<br><br>");
        out.println("<i style=\"font-size: 80px;color: #fff;\" class=\"fa fa-check-square-o\"></i>");
        out.println("<h2>Contato, <u>" + contato.getNome() + "</u> salvo!</h2>");
        out.print("</div></div>");
        out.println("</body>");
        out.println("</html>");
        
    }
    
    public void editar(HttpServletRequest request, HttpServletResponse response, String numero_busca) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Editar</title>");   
        out.println("<link rel=\"stylesheet\" href=\"css/estilo.css\">");
        out.print("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"cell\"><div class=\"tela\">");
        out.println("<div style=\"text-align: left;padding: 10px;\" title=\"Voltar\"><a class=\"back\" href=\"index.html\">"
        		+ "<i class=\"fa fa-arrow-left\"></i></a></div>");
	    out.println("<h1><i class=\"fa fa-edit\"></i> Editar</h1><br>"); 
        for (Contato c : agenda) {
            String numero = c.getNumero();
            if ( numero.equals(numero_busca) ) {
                out.println("<form method=\"get\" action=\"Agenda\">"
        	        		+ "<input type=\"text\" name=\"nome\" placeholder=\"Nome:\" value=\" "+ c.getNome() +"\"><br>"
        	        		+ "<br>"
        	        		+ "<input type=\"email\" name=\"email\" placeholder=\"Email: exemplo@exemplo.com\" value=\" "+ c.getEmail() +"\"><br>"
        	        		+ "<br>"
        	        		+ "<input type=\"text\" name=\"numero\" placeholder=\"Número do telefone:\" value=\" "+ c.getNumero() +"\"><br>"
        	        		+ "<br>"
        	        		+ "<input type=\"text\" name=\"rua\" placeholder=\"Rua\" style=\"width: 192px;\" value=\" "+ c.getEndereco().getRua() +"\">"
        	        		+ "<input type=\"text\" name=\"num\" placeholder=\"Nº:\" style=\"width: 60px;\" value=\" "+ c.getEndereco().getNumero() +"\"><br>"
        	        		+ "<br>"
        	        		+ "<input type=\"text\" name=\"bairro\" placeholder=\"Bairro \" value=\" "+ c.getEndereco().getBairro() +"\"><br>"
        	        		+ "<br>"
        	        		+ "<input type=\"text\" name=\"cidade\" placeholder=\"Cidade\" style=\"width: 192px;\" value=\" "+ c.getEndereco().getCidade() +"\">"
        	        		+ "<input type=\"text\" name=\"estado\" placeholder=\"UF:\" style=\"width: 60px;\" value=\" "+ c.getEndereco().getEstado() +"\"><br>"
        	        		+ "<br><br>"
        	        		+"<input type=\"hidden\" name=\"operacao\" value=\"cadastrar\"/>"
        	        		+ "<button class=\"btn\" type=\"reset\">Limpar</button>"
        	        		+ "<button class=\"btn\" type=\"submit\">Editar</button>"
        	        		+ "</form>");
                agenda.remove(c);
            } else {
            	 out.println("<h2>Contato não encontrado!</h2>");
            }
        }
        out.println("</div>"
        		+ "</div>"
        		+ "</body>"
        		+ "</html>");        
    }
    
    public void consultar(HttpServletRequest request, HttpServletResponse response, String nomeDoFormulario) throws IOException {
        PrintWriter out = response.getWriter();
        boolean flag = false;
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Buscar</title>");   
        out.println("<link rel=\"stylesheet\" href=\"css/estilo.css\">");
        out.print("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.print("<div class=\"cell\"><div class=\"tela\">");
        out.println("<div style=\"text-align: left;padding: 10px;\" title=\"Voltar\"><a class=\"back\" href=\"index.html\"><i class=\"fa fa-arrow-left\"></i></a></div>");
        out.println("<br><br>");
        out.println("<i style=\"font-size: 80px;color: #fff;\" class=\"fa fa-check-square-o\"></i>");
        out.println("<div class=\"dados\">");
        for (Contato c : agenda) {
            String contato = c.getNome().toLowerCase();
            if ( contato.equals(nomeDoFormulario.toLowerCase()) ) {
                flag = true;
                out.println( c.toString() );
            }
        }
        
        if (flag == false) {
            out.println("<h2>Contato não encontrado!</h2>");
        }
        out.print("</div></div></div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void excluir(HttpServletRequest request, HttpServletResponse response, String nome) throws IOException {
        for (Contato contato : agenda) {
            String c = contato.getNome();
            if ( c.equals(nome) ) {
                agenda.remove(contato);
                break;
            }
        }
        response.sendRedirect("index.html");
    }
}
