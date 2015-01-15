/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Login;
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
public class LoginDAO implements Map<Integer,Login>{
    public LoginDAO(){   
    }
    
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        boolean res = false;
        try {
            int login = (Integer) key;
            String sql = "SELECT * FROM Login where idLogin="+login;
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
    public Login get(Object key) {
        Login l = null;
        try {
            int login = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Login WHERE id_Login=" +login;
            ResultSet rs = stm.executeQuery(sql);
            
            if(rs.next()) {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                
                
                l = new Login(user,pass);
            }            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return l;
    }

    @Override
    public Login put(Integer key, Login value) {
        Login novo = null;
        CallableStatement cs = null;
        int id_login=0;
        String id = "select id_login.NEXTVAL from dual";
        try{
            String sql = "{call adiciona_login(?,?,?)}";
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id);
            synchronized(this){
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                    id_login=rs.getInt(1);
            }
                       
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id_login);
            cs.setString(2, value.getUser());
            cs.setString(3, value.getPass());

            cs.executeUpdate();
            
        }catch(SQLException e){ }
        
        return novo;
    }

    @Override
    public Login remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Login> m) {
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
            String sql = "SELECT id_Login from Login";
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
    public Collection<Login> values() {
        Collection<Login> res = new HashSet<>();
        try {
            String sql = "SELECT * FROM Login";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next())
                res.add(this.get(rs.getInt(1)));
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Set<Entry<Integer, Login>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
