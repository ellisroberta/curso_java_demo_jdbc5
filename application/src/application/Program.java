package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

    public static void main(String[] args) {

        Connection conn = null; // Inicializa a conexão como nula
        PreparedStatement st = null; // Inicializa o PreparedStatement como nulo

        try {
            // Obtém a conexão com o banco de dados
            conn = DB.getConnection();

            // Prepara a instrução SQL para deletar um departamento
            st = conn.prepareStatement(
                    "DELETE FROM department "
                            + "WHERE "
                            + "Id = ?"); // Importante usar WHERE para evitar a exclusão de todos os registros

            st.setInt(1, 5); // Define o ID do departamento a ser deletado

            // Executa a instrução de exclusão e obtém a quantidade de linhas afetadas
            int rowsAffected = st.executeUpdate();

            // Exibe o resultado da operação
            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            // Lança uma exceção personalizada para erros de integridade referencial
            throw new DbIntegrityException(e.getMessage());
        } finally {
            // Fecha o PreparedStatement e a conexão
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}