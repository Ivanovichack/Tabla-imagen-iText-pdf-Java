
package plantillapdfmain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Navi
 */
public class Plantilla {
    
    String nombreDirector;
    String fecha;
    String rutaImagen;
    List<Persona> personas;
    
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;
    
    public Plantilla(String nombreDirector,
            String fecha,
            String rutaImagen,
            List<Persona> personas)
    {
        this.nombreDirector = nombreDirector;
        this.fecha = fecha;
        this.rutaImagen = rutaImagen;
        this.personas = personas;
        
        documento = new Document();
        titulo  = new Paragraph("Plantilla Personalizada");        
    }
    
    public void crearPlantilla(){
        try {
            archivo = new FileOutputStream(nombreDirector + ".pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(1);

            Image image = null;
            try {
                image =  Image.getInstance(rutaImagen);//carga imagen
                image.scaleAbsolute(150, 100);//cambia tamaño
                image.setAbsolutePosition(415, 750);//coloca imagen en la posicion
                
            } catch (Exception e) {
            }
            
            documento.add(image);//agrega la imagen al documento
            
            documento.add(titulo);
            
            documento.add(new Paragraph("Director: " + nombreDirector));
            
            documento.add(Chunk.NEWLINE);
            
            Paragraph texto = new Paragraph("It is a long established fact that a reader will be distracted by the readable content of "
                    + "a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution"
                    + " of letters, as opposed to using 'Content here, content here', making it look like readable English. "
                    + "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for "
                    + "'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, "
                    + "sometimes on purpose (injected humour and the like).");
            texto.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(texto);
            
            documento.add(Chunk.NEWLINE);
            
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            PdfPCell name = new PdfPCell(new Phrase("Nombre"));
            name.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell age = new PdfPCell(new Phrase("Edad"));
            age.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell tel = new PdfPCell(new Phrase("Telefono"));
            tel.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell address = new PdfPCell(new Phrase("Dirección"));
            address.setBackgroundColor(BaseColor.ORANGE);
            
            tabla.addCell(name);
            tabla.addCell(age);
            tabla.addCell(tel);
            tabla.addCell(address);            
            
            for(Persona persona: this.personas){
                tabla.addCell(persona.getNombre());                
                tabla.addCell(persona.getEdad()+"");
                tabla.addCell(persona.getTelefono());
                tabla.addCell(persona.getDireccion());                
            }
            documento.add(tabla);          
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Fecha: " + fecha));
            
            documento.close();
            JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch(DocumentException e){
            System.err.println(e.getMessage());
        }
        
    }
    
    
    
    
}
