package ClassesDAO;

import Classes.Encomenda;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/** ESTÁ
 *
 * @author Tiago
 */
public class EncomendaDAO implements Map<Integer,Encomenda>{
    private int idcliente;
    
    public EncomendaDAO() {
    
    }
    
    public EncomendaDAO(int id_cliente) {
        this.idcliente=id_cliente;
    }

    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Encomenda  WHERE c_id_cliente= "+this.idcliente+" and activa=1";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
                res++;    
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
            String sql = "SELECT * FROM Encomenda where c_id_cliente=" + this.idcliente;
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
            int id = (Integer) key;
            String sql = "SELECT * FROM Encomenda WHERE cd_id_encomenda= "+ this.idcliente + " and id_encomenda = " + id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Encomenda get(Object key) {
        Encomenda enc = null;
        try {
            int id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Encomenda WHERE c_id_encomenda = "+this.idcliente+" and id_cliente=" +id + " and activa =1";
            ResultSet rs = stm.executeQuery(sql);
            
            if(rs.next()) {
                int id_encomenda = rs.getInt(1);
                int factura = rs.getInt(2);
                int banheiras = rs.getInt(3);
                int activa = rs.getInt(4);
                int c_id_cliente = rs.getInt(5);
                                
                enc = new Encomenda(id_encomenda,factura,banheiras,activa,c_id_cliente);
            }            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return enc;

    }
    //FALTA VER ISTO
    @Override
    public Encomenda put(Integer key, Encomenda value) {
        Encomenda enc = null;
        CallableStatement cs = null;
        CallableStatement cs2 = null;
        CallableStatement cs3 = null;
        int id_encomenda=0;
        int factura=0;
        String id = "select id_produto.NEXTVAL from dual";
        String fact = "select id_factura.NEXTVAL from dual";
        try{
            String sql = "{call adiciona_encomenda(?,?,?,?,?)}";
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id);
            synchronized(this){
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                    id_encomenda=rs.getInt(1);
            }
            PreparedStatement pst2 = ConexaoBD.getConexao().prepareStatement(fact);
            synchronized(this){
                ResultSet rs = pst2.executeQuery();
                if(rs.next())
                    factura=rs.getInt(1);
            }
            String sql2 = "{call aleat_banheiras}";
            cs2 = ConexaoBD.getConexao().prepareCall(sql2);
            int banheiras=cs2.executeUpdate();
            String sql3 = "{call escolhe_cliente";
            cs3 = ConexaoBD.getConexao().prepareCall(sql3);
            int escolhe_cliente=cs2.executeUpdate();
            
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id_encomenda);
            cs.setInt(2, factura);
            cs.setInt(3, banheiras);
            cs.setInt(4, 1);
            cs.setInt(5, escolhe_cliente);
            cs.executeUpdate();
            
        }catch(SQLException e){ }
        
        return enc;
    }

    @Override
    public Encomenda remove(Object key) {
        Encomenda enc = null;
        CallableStatement cs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_encomenda(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id);
            cs.execute();
        } catch (SQLException e) {
        }
        return enc;   
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT id_encomenda FROM Encomenda WHERE activa = 1";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next())
                res.add(rs.getInt(1));
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Collection<Encomenda> values() {
        Collection<Encomenda> res = new HashSet<>();
        try {
            String sql = "SELECT factura FROM Encomenda WHERE activa= 1";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next())
                res.add(this.get(rs.getString(2)));
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Set<Entry<Integer, Encomenda>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Encomenda> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
