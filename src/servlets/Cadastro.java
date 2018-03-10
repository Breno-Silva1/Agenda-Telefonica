package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contato;
import entidades.Endereco;
import negocio.Agenda;
@WebServlet("/Cadastro")
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Cadastro() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Agenda agenda = new Agenda();
		Contato contato = new Contato();
		Endereco endereco = new Endereco();
		PrintWriter out = response.getWriter();
		
		//Contato
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String numero = request.getParameter("numero");
		
		//Endereço
		String rua = request.getParameter("rua");
		String num = request.getParameter("num");
		int numcasa = Integer.parseInt(num);
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		
		endereco = new Endereco(rua, numcasa, bairro, cidade, estado);
		contato = new Contato(nome, email, numero, endereco); 
		agenda.cadastrar(contato);
		
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
        out.println("<h2>Contato, <u>" + contato.getNome() + "</u> cadastrado!</h2>");
        out.print("</div></div>");
        out.println("</body>");
        out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
