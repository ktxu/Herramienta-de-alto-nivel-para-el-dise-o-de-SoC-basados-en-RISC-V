package FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_TXT {
    
    public File_TXT()
    {   
    }// fin constructor
    
    public static boolean verifyFilenameExists(String nombreArchivo){
        String nombreArchivoExtension;
        if(!nombreArchivo.endsWith(".c")) // si no tiene extensión .txt lo agrega
            nombreArchivoExtension = nombreArchivo + ".c";
         else
            nombreArchivoExtension = nombreArchivo;
        File file = new File( nombreArchivoExtension );
        return file.exists();
    }
    
    public static void writeText(String nombreArchivo, String texto) throws IOException
    {
        String nombreArchivoExtension;
        if(!nombreArchivo.endsWith(".c")) // si no tiene extensión .txt lo agrega
            nombreArchivoExtension = nombreArchivo + ".c";
         else
            nombreArchivoExtension = nombreArchivo;
        // GENERA EL ARCHIVO PARA GUARDAR
        File file = new File( nombreArchivoExtension );
        // creates the file
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file); 
        // Writes the content to the file
        writer.write( texto ); 
        writer.flush();
        writer.close();
    }
    
    public static String readText(String nombreArchivo) throws IOException
    {
        String textoCargado = "";
        BufferedReader in = new BufferedReader(new FileReader(nombreArchivo));
        String line = in.readLine();
        while(line != null){
            textoCargado += line + "\n";
            line = in.readLine();
        }
        return textoCargado;
    }
}// fin clase
