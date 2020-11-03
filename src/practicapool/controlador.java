package practicapool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class controlador {
    public String ruta;
    public ArrayList<Double> lista = new ArrayList<Double>();
    public double obtmedia;
    public controlador(String ruta) {
        this.ruta = ruta;
    }//

 
    public void loaddata()
    { 
        String cadena;
       try 
        {
            File archivo = new File(this.ruta);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            while((cadena = br.readLine())!=null){
            lista.add(Double.parseDouble(cadena));
            }               
        }
       catch (Exception e)
       {
           System.err.println("");
       }
    }
    public double media()
    {
        double promedio = 0;
        int i;
        double media = 0;
        for(i=0;i<lista.size();i++)
        {
           promedio+=lista.get(i);
        }//cierre forpromedio+=lista
        media=promedio/this.lista.size();
        obtmedia=media;
        return media;
        
    }
    
    public double Varianza()
    {
        double varianza=0;
        double contador=0;
        double obtarreglo=0;
        for(int i=0;i<lista.size();i++)
        {
           
           varianza  += (Math.pow(lista.get(i)-obtmedia, 2))/this.lista.size();
          
        }
        return varianza;
    }
    
    public ArrayList<Integer> Binario(){
        ArrayList<Integer> binario = new ArrayList();
        double[ ] va = new double[lista.size()];
        
        for(int i=0;i<lista.size();i++)
        {        
           va[i]=lista.get(i); 
        }
        
        for(int i=1; i < va.length; i++){
            if(va[i]<=va[i-1]) binario.add(0);
            else binario.add(1);
        }
        return binario;
    }

    public int corridas(){
        int corridas = 1;
        ArrayList<Integer> binario = this.Binario();
        for(int i = 1; i<binario.size();i++){
            if(binario.get(i) != binario.get(i-1)){
                corridas++;
            }
        }
        return corridas;
    }

    public double pruebacorrida(){
        double zo;
        zo = Math.abs((this.corridas() - this.media())/Math.sqrt(this.Varianza()));
        return zo;
    }
    
    public void ImprimirBinario(){


        int contc=0;
        for(int i=0; i<this.Binario().size(); i++){
            frmPrincipal.txtNumeros.append(String.valueOf(this.Binario().get(i)+" "));
            contc++;
            if(contc==8){
            frmPrincipal.txtNumeros.append("\n");
            contc=0;
            }
        }
    }
    
    public void Imprimir(){
         
         this.ImprimirBinario();
    }
    
    public void resultadoFinal(double conf){
        frmPrincipal.jLabel2.setText(String.valueOf(String.format("%.5f", this.pruebacorrida())) + " < " + String.valueOf(conf));
        if(this.pruebacorrida()<conf){
            frmPrincipal.abajo.setText("El conjunto Ri es Independiente");
        }else{
            frmPrincipal.abajo.setText("El conjunto Ri es dependiente");
        }
    }
}//cierre controlador

  

