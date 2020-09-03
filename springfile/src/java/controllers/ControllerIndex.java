package controllers;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerIndex 
{    
    @Autowired private ServletContext context;
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
//        String fileDirectory = context.getContextPath() + "/files/"; 
//        System.out.println("ContextPath : "+fileDirectory);    
//        fileDirectory = context.getRealPath("/") + "/files/"; 
//        System.out.println("RealPath : "+fileDirectory);
        
        try 
        {
            InputStream myfile = new FileInputStream(new File("C:\\video.mp4"));
            byte[] filebytes = IOUtils.toByteArray(myfile);
            
            System.out.println("filebytes : "+filebytes.length);
            
            String fileName = "video1.mp4";
            //String filePath="C:\\Users\\pc\\Desktop";
            //String filePath = getServletContext().getRealPath("/") + "/pdfin/";
            String filePath = context.getRealPath("/") + "/files/";
            System.out.println("filePath : "+filePath);
            
            File file = new File(filePath, fileName);               
            OutputStream os = new FileOutputStream(file);
            os.write(filebytes); 
            
            
            String link="files/"+fileName;
            link="<a href='"+link+"' target='_blank'>Download</a>";
            model.addAttribute("link", link);
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Error : "+e);
        }        
        
        return "index";
    }
     
}
