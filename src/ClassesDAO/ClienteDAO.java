/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Cliente;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Tiago
 */
public class ClienteDAO implements Map<Integer,Cliente>{
    private int idcliente;
    
    public ClienteDAO() {
     
    }
    
    public ClienteDAO(int id_cliente) {
        this.idcliente=id_cliente;
    }

    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Cliente where activo=1"; //WHERE a_id_armazem = "+this.idarmazem + " and e_id_encomenda=" + this.idencomenda;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                res++;
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        boolean res = false;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Cliente where activo=1";
            ResultSet rs = stm.executeQuery(sql);
            if (!rs.next()) {
                res = true;
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean res = false;
        try {
            Integer id = (Integer) key;
            String sql = "SELECT * FROM Cliente where activo=1 and id_cliente= "+id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente get(Object key) {
        Cliente c = null;
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Cliente WHERE id_cliente= " + id;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                c = new Cliente(id, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
                
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return c;
    }

    @Override
    public Cliente put(Integer key, Cliente value) {
        Cliente c = null;
        PreparedStatement pst = null;
        int id_cliente = 0;
        String id = "select id_cliente.NEXTVAL from dual";

        try {
            //String sql = "{call adiciona_cliente(?,?,?,?,?,?,?)}";
            String sql = "insert into cliente values(?,?,?,?,?,?,?)";
            
            PreparedStatement pst2 = ConexaoBD.getConexao().prepareStatement(id);
            synchronized (this) {
                ResultSet rs = pst2.executeQuery();
                if (rs.next()) {
                    id_cliente = rs.getInt(1);
                }
                rs.close();
            }
            
            pst = ConexaoBD.getConexao().prepareStatement(sql);
            pst.setInt(1, id_cliente);
            pst.setString(2, value.getNome_farmacia());
            pst.setString(3, value.getNome_farmaceutico());
            pst.setInt(4, value.getContacto());
            pst.setInt(5, value.getNif());
            pst.setInt(6, value.getActivo());
            pst.setInt(7, value.getLocal_id_local());
            int i = pst.executeUpdate();
            c = value;
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    @Override
    public Cliente remove(Object key) {
        Cliente c = null;

        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_cliente(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
            ConexaoBD.fecharCursor(rs, cs);

        } catch (SQLException e) {
        }
        return c;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Cliente> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Cliente WHERE activo = 1";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                res.add(rs.getInt(1));
            }

            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
            
        }
        return res;
    }

    @Override
    public Collection<Cliente> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, Cliente>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
