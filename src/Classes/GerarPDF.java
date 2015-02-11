package Classes;

/**
 *
 * @author Tiago
 */
import GUI.OCP;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.io.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class GerarPDF {

    private OCP o;

    public GerarPDF(OCP o) throws BadElementException, IOException {
        this.o = o;
        Map<Integer, String[]> clientes = this.o.getSistema().parseClientes();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Rotas.pdf"));
            document.open();
            int j = 1;
            for (int i : this.o.getSistema().getRotasEscolhidas().keySet()) {
                int escolhida = this.o.getSistema().getRotasEscolhidas().get(i).getEscolhida();
                Image image1 = Image.getInstance("OCPPortugal.png");
                document.add(image1);
                image1.setAbsolutePosition(1000f, 650f);
                com.itextpdf.text.Font fontbold = FontFactory.getFont("Times-Roman", 24, Font.BOLD);
                document.add(new Paragraph("\nRota " + j, fontbold));
                com.itextpdf.text.Font fontAtributos = FontFactory.getFont("Times-Roman", 14,Font.BOLD);
                int id_func = this.o.getSistema().getRotas().get(escolhida).getR_id_funcionario();
                int id_veic = this.o.getSistema().getRotas().get(escolhida).getR_id_veiculo();
                document.add(new Paragraph("Motorista: " + this.o.getSistema().getFuncionarios().get(id_func).getNome(), fontAtributos));
                document.add(new Paragraph("Veiculo: " + this.o.getSistema().getVeiculos().get(id_veic).getMarca()+" | "+this.o.getSistema().getVeiculos().get(id_veic).getMatricula(), fontAtributos));
                document.add(Chunk.NEWLINE);
                com.itextpdf.text.Font font = FontFactory.getFont("Times-Roman", 12);

                int id_rota = this.o.getSistema().getRotasEscolhidas().get(i).getEscolhida();
                String[] s = clientes.get(id_rota);

                for (int k = 0; k < s.length; k++) {
                    int cli = Integer.parseInt(s[k]);
                    Cliente v = this.o.getSistema().getClientes().get(cli);
                    Local l = this.o.getSistema().getLocais().get(v.getLocal_id_local());
                    document.add(new Paragraph("----\nFarmácia: "+v.getNome_farmacia() +
                                               "\nMorada: "+l.getMorada() +
                                               "\nConcelho: "+l.getConcelho(), font));
                }
                document.newPage();
                j++;
            }
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(OCP.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(OCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("C:\\Users\\Tiago\\Documents\\NetBeansProjects\\PI-TD1\\Rotas.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }
}
