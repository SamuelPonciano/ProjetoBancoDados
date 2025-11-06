package database;

class CriarTabelas {
    public static void criar(Statement stmt) throws SQLException {
        // Tabela de Planos
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS clientes (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "sexo CHAR('F', 'M', 'O'), " +
                        "idade int, " +
                        "dataNasc DATETIME" +
                        ")"
        );

        // Tabela de Instituições
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS institutions (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "institution_name VARCHAR(255) NOT NULL, " +
                        "social_cause VARCHAR(255) NOT NULL, " +
                        "zipcode VARCHAR(10), " +  // CEP
                        "street VARCHAR(255), " +
                        "number VARCHAR(50), " +
                        "neighborhood VARCHAR(255), " +
                        "city VARCHAR(255), " +
                        "state VARCHAR(2), " +  // Sigla de 2 caracteres
                        "country VARCHAR(100) DEFAULT 'Brasil', " +
                        "complement VARCHAR(255), " +
                        "plan_id BIGINT, " +
                        "FOREIGN KEY (plan_id) REFERENCES plans(id)" +
                        ")"
        );

        // Tabela de Usuários (herança SINGLE_TABLE)
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "user_type VARCHAR(31) NOT NULL, " +  // Para discriminar ADMIN/USER
                        "login VARCHAR(255) NOT NULL, " +
                        "email VARCHAR(255) NOT NULL, " +
                        "password VARCHAR(255) NOT NULL, " +
                        "creation_date DATETIME, " +
                        "institution_id BIGINT, " +
                        "FOREIGN KEY (institution_id) REFERENCES institutions(id)" +
                        ")"
        );

        // Tabela de Arquivos (herança SINGLE_TABLE)
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS files (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "file_type VARCHAR(31) NOT NULL," +
                        "file_name VARCHAR(255) NOT NULL, " +
                        "file_size INT, " +
                        "file_release_date DATETIME, " +
                        "file_location VARCHAR(255), " +
                        "file_url VARCHAR(255)," +
                        "duration_in_seconds BIGINT," +
                        "user_id BIGINT NOT NULL," +
                        "create_date DATETIME, " +
                        "last_modifier DATETIME, " +
                        "frequency BIGINT, " +
                        "FOREIGN KEY (user_id) REFERENCES users(id)" +
                        ");"
        );

        // Tabela de Permissões de Arquivo (ElementCollection)
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS file_permissions (" +
                        "file_id BIGINT NOT NULL, " +
                        "user_id BIGINT NOT NULL, " +
                        "permission VARCHAR(50) NOT NULL, " +
                        "PRIMARY KEY (file_id, user_id, permission), " +
                        "FOREIGN KEY (file_id) REFERENCES files(id)," +
                        "FOREIGN KEY (user_id) REFERENCES users(id)" +
                        ")"
        );

        // Tabela de Comentários
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS comments (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "body TEXT, " +
                        "time DATETIME, " +
                        "author_id BIGINT NOT NULL, " +
                        "file_id BIGINT NOT NULL, " +
                        "FOREIGN KEY (author_id) REFERENCES users(id), " +
                        "FOREIGN KEY (file_id) REFERENCES files(id)" +
                        ")"
        );

        // Tabela de Operações de Arquivo
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS file_operations (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "file_id BIGINT NOT NULL, " +
                        "user_id BIGINT NOT NULL, " +
                        "receiver_id BIGINT, " +
                        "operation_date DATETIME NOT NULL, " +
                        "operation_type VARCHAR(50) NOT NULL, " +
                        "FOREIGN KEY (file_id) REFERENCES files(id), " +
                        "FOREIGN KEY (user_id) REFERENCES users(id), " +
                        "FOREIGN KEY (receiver_id) REFERENCES users(id)" +
                        ")"
        );

        // Tabela de Solicitações de Suporte
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS support_requests (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "author_id BIGINT NOT NULL, " +
                        "admin_id BIGINT, " +
                        "title VARCHAR(255) NOT NULL, " +
                        "body TEXT NOT NULL, " +
                        "file_id BIGINT, " +
                        "status VARCHAR(50) NOT NULL, " +
                        "creation_date DATETIME, " +
                        "resolved_date DATETIME, " +
                        "FOREIGN KEY (author_id) REFERENCES users(id), " +
                        "FOREIGN KEY (admin_id) REFERENCES users(id), " +
                        "FOREIGN KEY (file_id) REFERENCES files(id)" +
                        ")"
        );

        // Tabela de Histórico de Versões
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS versions_historical (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "creation_date DATETIME, " +
                        "editor_id BIGINT NOT NULL, " +
                        "file_id BIGINT NOT NULL, " +
                        "commit_message TEXT NOT NULL, " +
                        "FOREIGN KEY (editor_id) REFERENCES users(id), " +
                        "FOREIGN KEY (file_id) REFERENCES files(id)" +
                        ")"
        );

        inserirDados(stmt);
    }

    private void inserirDados(Statement stmt){
        for(int i = 0; i<100; i++){
            String nome = "cliente" + i;

            String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?,?);";
            
        }

        for(int i = 0; i<20; i++){
            String nome = "produto" + i;
        }

        for(int i = 0; i<5; i++){
            String nome = "cargo" + i;

        }
    }
}