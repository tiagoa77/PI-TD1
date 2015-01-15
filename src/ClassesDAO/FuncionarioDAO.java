/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Funcionario;
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
public class FuncionarioDAO implements Map<Integer,Funcionario>{
    private int idarmazem;
    
    public FuncionarioDAO(){
        
    }
    
    public FuncionarioDAO(int id_armazem) {
        this.idarmazem=id_armazem;
    }
    
    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Funcionario where activo=1"; //WHERE a_id_armazem = "+this.idarmazem + " and e_id_encomenda=" + this.idencomenda;
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
            String sql = "SELECT * FROM Funcionario where activo=1";
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
            String sql = "SELECT * FROM Funcionario where activo=1 and id_funcionario = "+id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    
    @Override
    public Funcionario get(Object key) {
        Funcionario f = null;
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Funcionario WHERE id_funcionario= " + id + " and activo=1";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                f = new Funcionario(id, rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5), rs.getString(6),rs.getInt(7),rs.getInt(8));
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return f;
    }

    @Override
    public Funcionario put(Integer key, Funcionario value) {
        Funcionario f = null;
        CallableStatement cs = null;
        int id_funcionario = 0;
        String id = "select id_funcionario.NEXTVAL from dual";

        try {
            String sql = "{call adiciona_funcionario(?,?,?,?,?,?,?,?)}";
            
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id); //SERVE PARA CHAMAR AS SEQUENCIAS
            synchronized (this) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id_funcionario = rs.getInt(1);
                }
            }
            
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id_funcionario);
            cs.setString(2, value.getNome());
            cs.setString(3, value.getData_nasc());
            cs.setString(4, value.getMorada());
            cs.setInt(5, value.getContacto());
            cs.setString(6, value.getFuncao());
            cs.setInt(7, value.getActivo());
            cs.setInt(8, value.getArmazem_id_armazem());
            cs.executeUpdate();
            f = value;
            cs.close();
        } catch (SQLException e) {
        }
        return f;
    }

    @Override
    public Funcionario remove(Object key) {
        Funcionario f = null;

        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_funcionario(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
            ConexaoBD.fecharCursor(rs, cs);

        } catch (SQLException e) {
        }
        return f;
    }

    

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Funcionario WHERE activo = 1";
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
    public Collection<Funcionario> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, Funcionario>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void putAll(Map<? extends Integer, ? extends Funcionario> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
