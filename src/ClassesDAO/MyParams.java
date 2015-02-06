/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import GUI.OCP;
import ilog.opl.*;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class MyParams extends IloCustomOplDataSource {

    private OCP ocp;
    //int[][] clientesRotas;
    
    
    public MyParams(IloOplFactory oplF, OCP ocp) {
        super(oplF);
        this.ocp = ocp;
    }
    

    public void customRead() {
        IloOplDataHandler handler = getDataHandler();
        
        //Numero Veiculos
        handler.startElement("K");
        handler.addIntItem(this.ocp.getSistema().getVeiculos().size());
        handler.endElement();
        
        //Numero Clientes
      
        handler.startElement("V");
        handler.startSet();
        for (int i : this.ocp.keysetClientesVisitados()) {
            handler.addIntItem(i);
        }
        handler.endSet();
        handler.endElement();
        
        handler.startElement("Clientes");
        handler.startIndexedArray();
        for (int i : this.ocp.keysetClientesVisitados()) {
            handler.setItemIntIndex(i);
            handler.addIntItem(i);
        }
        handler.endIndexedArray();
        handler.endElement();

        //Numero Rotas
        
        handler.startElement("R");
        handler.startSet();
        for (int i : this.ocp.getSistema().getRotas().keySet()) {
            handler.addIntItem(i);
        }
        handler.endSet();
        handler.endElement();
        
        handler.startElement("Rotas");
        handler.startIndexedArray();
        for (int i : this.ocp.getSistema().getRotas().keySet()) {
            handler.setItemIntIndex(i);
            handler.addIntItem(i);
        }
        handler.endIndexedArray();
        handler.endElement();
       

        //Numero Custos 
     /*   
        handler.startElement("C");
        handler.startSet();
        for (int i : this.sistema.getRotas().keySet()) {
            handler.addIntItem(i-1);
        }
        handler.endSet();
        handler.endElement();
       */ 
        
        Map<Integer,String[]> clientes = this.ocp.getSistema().parseClientes();
        
        int Conta=0;
        //Matriz ClientesRotas
        handler.startElement("ClientesRotas");
        handler.startIndexedArray();
        for (int i : this.ocp.keysetClientesVisitados()) {
            handler.setItemIntIndex(i);
            handler.startIndexedArray();
            for (int j : this.ocp.getSistema().getRotas().keySet()) {
                for(int k=0;k<clientes.get(j).length;k++){
                    handler.setItemIntIndex(j);
                    if (Integer.parseInt(clientes.get(j)[k])==i) {
                        handler.addIntItem(1);
                        Conta++;
                    }
                }
            }
            handler.endIndexedArray();
        }
        handler.endIndexedArray();
        handler.endElement();
        
        System.out.println(Conta);
        int total;
        int c1,c2;
        handler.startElement("Custos");
        handler.startIndexedArray();
        for (int i: this.ocp.getSistema().getRotas().keySet()) {
            total=0;
            handler.setItemIntIndex(i);
            for(int j=1;j<this.ocp.getSistema().getTamanho().get(i);j++){
                c1= Integer.parseInt(clientes.get(i)[j-1]);
                c2= Integer.parseInt(clientes.get(i)[j]);
                //System.out.println("Rotas: " + i + "   c1="+c1+"    c2="+ c2+ "   Dist: "+this.sistema.devolve_distancias(c1,c2));
                total+=this.ocp.getSistema().devolve_distancias(c1,c2);
            }
            handler.addIntItem(total);
        }
        handler.endIndexedArray();
        handler.endElement();
        
    }
};
