package pl.arabowski.bookweb.db;

import org.hibernate.dialect.MySQL8Dialect;

public class MySqlCustomDialect extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    }
}
