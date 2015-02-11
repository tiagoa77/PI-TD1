package ClassesDAO;

import Classes.RotasEscolhidas;
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
public class RotasEscolhidasDAO implements Map<Integer, RotasEscolhidas> {

    public RotasEscolhidasDAO() {

    }

    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM ROTAS_ESCOLHIDAS";
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
            String sql = "SELECT * FROM ROTAS_ESCOLHIDAS";
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
            String sql = "SELECT * FROM ROTAS_ESCOLHIDAS  WHERE id_rota_escolhida = " + id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public RotasEscolhidas get(Object key) {
        RotasEscolhidas ro = null;
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM ROTAs_ESCOLHIDAs WHERE id_rota_escolhida= " + id;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                ro = new RotasEscolhidas(rs.getInt(1), rs.getInt(2));
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return ro;
    }

    @Override
    public RotasEscolhidas put(Integer key, RotasEscolhidas value) {
        RotasEscolhidas r = null;
        PreparedStatement pst = null;
        //CallableStatement cs = null;
        PreparedStatement pst2 = null;
        int id_rota=0;
        try {
            String sql = "select id_rota_escolhida.nextval from dual";
            //String sql2 = "adiciona_rotas_escolhidas(?,?)";
            String sql2 = "insert into rotas_escolhidas values(?,?)";
            pst = ConexaoBD.getConexao().prepareStatement(sql);
            synchronized(this){
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                    id_rota=rs.getInt(1);
            }
            pst2 = ConexaoBD.getConexao().prepareStatement(sql2);
            pst2.setInt(1, id_rota);
            pst2.setInt(2, value.getEscolhida());
            pst2.executeUpdate();
            r = value;
            pst.close();
            pst2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return r;
    }

    @Override
    public RotasEscolhidas remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM rotas_escolhidas";
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
    public Collection<RotasEscolhidas> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends RotasEscolhidas> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, RotasEscolhidas>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
