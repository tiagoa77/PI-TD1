package ClassesDAO;

import Classes.Rota;
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
public class RotaDAO implements Map<Integer,Rota>{

    public RotaDAO() {
        
    }

    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM ROTA";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                res++;
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {}
        return res;
    }

    @Override
    public boolean isEmpty() {
        boolean res = false;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM ROTA";
            ResultSet rs = stm.executeQuery(sql);
            if(!rs.next())
                res=true;
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
            String sql = "SELECT * FROM ROTA r WHERE r.id_rota = "+id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    

    @Override
    public Rota get(Object key) {
        Rota ro = null;
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM ROTA WHERE id_rota= "+id;
            ResultSet rs = stm.executeQuery(sql);
            
            if(rs.next()) {
                ro = new Rota(rs.getInt(1),rs.getDate(2),rs.getNString(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
            }            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ro;
    }

    @Override
    public Rota put(Integer key, Rota value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rota remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM rota";
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
    public Collection<Rota> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, Rota>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void putAll(Map<? extends Integer, ? extends Rota> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
