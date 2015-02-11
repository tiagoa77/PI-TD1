/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Local;
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
public class LocalDAO implements Map<Integer,Local>{

    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Local";
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
            String sql = "SELECT * FROM Local";
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
            String sql = "SELECT * FROM Local where id_local="+id;
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
    public Local get(Object key) {
        Local l = null;
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Local WHERE id_local= " + id;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                l = new Local(id, rs.getString(2), rs.getString(3));
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return l;
    }

    @Override
    public Local put(Integer key, Local value) {
        Local l = null;
        CallableStatement cs = null;
        int id_local = 0;
        String id = "select id_local.NEXTVAL from dual";

        try {
            String sql = "{call adiciona_local(?,?,?)}";
            
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id); //SERVE PARA CHAMAR AS SEQUENCIAS
            
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id_local = rs.getInt(1);
                    rs.close();
                }
            
            
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id_local);
            cs.setString(2, value.getMorada());
            cs.setString(3, value.getConcelho());
            cs.executeUpdate();
            l = new Local(id_local, value.getMorada(), value.getConcelho());
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    @Override
    public Local remove(Object key) {
        Local l = null;

        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_local(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
            ConexaoBD.fecharCursor(rs, cs);

        } catch (SQLException e) {
        }
        return l;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Local> m) {
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
            String sql = "SELECT * FROM Local";
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
    public Collection<Local> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, Local>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
