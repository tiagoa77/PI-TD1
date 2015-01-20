/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Sistema;
import ilog.concert.IloException;
import ilog.cplex.*;
import ilog.opl.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class MyParams extends IloCustomOplDataSource {

    private Sistema sistema;
    int[][] clientesRotas;

    public MyParams(IloOplFactory oplF, int[][] cR, Sistema s) {
        super(oplF);
        this.clientesRotas = cR;
        this.sistema = s;
    }

    public void customRead() {
        IloOplDataHandler handler = getDataHandler();
        
        //Numero Veiculos
        handler.startElement("K");
        handler.addIntItem(this.sistema.getVeiculos().size());
        handler.endElement();
        
        //Numero Clientes
        handler.startElement("V");
        handler.startSet();
        for (int i : this.sistema.getClientes().keySet()) {
            handler.addIntItem(i);
        }
        handler.endSet();
        handler.endElement();
        
        handler.startElement("Clientes");
        handler.startIndexedArray();
        for (int i : this.sistema.getClientes().keySet()) {
            handler.addIntItem(i);
        }
        handler.endIndexedArray();
        handler.endElement();

        //Numero Rotas
        
        handler.startElement("R");
        handler.startSet();
        for (int i : this.sistema.getRotas().keySet()) {
            handler.addIntItem(i);
        }
        handler.endSet();
        handler.endElement();
        
        handler.startElement("Rotas");
        handler.startIndexedArray();
        for (int i : this.sistema.getRotas().keySet()) {
            handler.addIntItem(i);
        }
        handler.endIndexedArray();
        handler.endElement();
       

        //Numero Custos 

        ArrayList<Integer> distancias = new ArrayList<>();

        for (int i : this.sistema.getDistancias().keySet()) {
            distancias.add(i);
        }
        
        System.out.println("distancias: "+distancias.size());
        
        
        Map<Integer,String[]> clientes = this.sistema.parseClientes();
        int total;
        handler.startElement("Custos");
        handler.startIndexedArray();
        for (int i: this.sistema.getRotas().keySet()) {
            total=0;
            for(int j=0;j<clientes.get(i).length;j++){
                total+=this.sistema.devolve_distancias(j,j+1);
            }
            handler.addIntItem(total);
        }
        handler.endIndexedArray();
        handler.endElement();
        

        //Matriz ClientesRotas
        handler.startElement("ClientesRotas");
        handler.startArray();
        System.out.println("TAMANHO:" + this.clientesRotas.length);
        for (int i = 0; i < this.clientesRotas.length; i++) {
            handler.startArray();
            for (int j = 0; j < clientesRotas[i].length; j++) {
                handler.addIntItem(clientesRotas[i][j]);
            }
            handler.endArray();
        }
        handler.endArray();
        handler.endElement();
    }
}
