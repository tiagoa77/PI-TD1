package ClassesDAO;

import Classes.Produto;
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

/**
 *
 * @author Tiago
 */
public class ProdutoDAO implements Map<Integer, Produto> {

    private int idarmazem;
    private int idencomenda;

    public ProdutoDAO() {

    }

    //VER ISTO

    public ProdutoDAO(int id_armazem, int id_encomenda) {
        this.idencomenda = id_encomenda;
        this.idarmazem = id_armazem;
    }

    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Produto where activo=1"; //WHERE a_id_armazem = "+this.idarmazem + " and e_id_encomenda=" + this.idencomenda;
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
            String sql = "SELECT * FROM PRODUTO where activo=1";
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
            String sql = "SELECT * FROM PRODUTO where activo=1 and id_produto = "+id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Produto get(Object key) {
        Produto prod = null;
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM PRODUTO WHERE id_produto= " + id + " and activo=1";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                prod = new Produto(id, rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return prod;
    }

    @Override
    public Produto put(Integer key, Produto value) {
        Produto prod = null;
        CallableStatement cs = null;
        int id_produto = 0;
        int encomenda=0;
        

        try {
            String id = "select id_produto.NEXTVAL from dual";
            String sql = "{call adiciona_produto(?,?,?,?,?,?,?,?)}";
            String sql2 = "select escolhe_encomenda from dual";
            
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id); //SERVE PARA CHAMAR AS SEQUENCIAS
            synchronized (this) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id_produto = rs.getInt(1);
                }
            }
            
            PreparedStatement pst2 = ConexaoBD.getConexao().prepareStatement(sql2);
            synchronized (this) {
                ResultSet rs = pst2.executeQuery();
                if (rs.next()) {
                    encomenda = rs.getInt(1);
                }
            }
            
            

            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id_produto);
            cs.setString(2, value.getNome());
            cs.setDouble(3, value.getPreco());
            cs.setString(4, value.getTipo());
            cs.setString(5, value.getDescricao());
            cs.setInt(6, value.getActivo());
            cs.setInt(7, value.getArmazem_id_armazem());
            cs.setInt(8, encomenda);
            int put = cs.executeUpdate();
            prod = value;
            cs.close();
        } catch (SQLException e) {
        }
        return prod;
    }

    @Override
    public Produto remove(Object key) {
        Produto prod = null;

        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_produto(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
            ConexaoBD.fecharCursor(rs, cs);

        } catch (SQLException e) {
        }
        return prod;
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Produto p WHERE p.activo = 1";
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
    public Collection<Produto> values() {
        Collection<Produto> res = new HashSet<>();
        try {
            String sql = "SELECT nome FROM Produto p WHERE p.activo= 1";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                res.add(this.get(rs.getString(2)));
            }

            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Set<Entry<Integer, Produto>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Produto> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
