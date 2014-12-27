package ClassesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {

    public static final String ip = "localhost";
    public static final String porta = "1521";
    public static final String sid = "orcl";
    public static final String user = "Trabalho";
    public static final String pw = "oracle";
    public static final String url = "jdbc:oracle:thin:@" + ip + ":" + porta + ":" + sid;

    public static Connection conexao;

    public static void iniciarConexao() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexao = DriverManager.getConnection(url, user, pw);
            conexao.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static Connection getConexao() {
        return ConexaoBD.conexao;
    }

    public static void terminarConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
        }
    }

    public static void fecharCursor(ResultSet rs, Statement stm) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
