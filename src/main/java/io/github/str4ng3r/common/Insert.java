package io.github.str4ng3r.common;

import io.github.str4ng3r.exceptions.InvalidSqlGenerationException;

import java.util.ArrayList;
import java.util.List;

public class Insert {

    StringBuilder columns;
    List<Object> values;

    StringBuilder valuesQuestion;

    String table;

    public Insert() {
        columns = new StringBuilder();
        values = new ArrayList<>();
        valuesQuestion = new StringBuilder();
    }
    public Insert(String table) {
        this();
    }

    public Insert setColumns(String... columns) {
        this.columns.append(String.join(",", columns));
        return this;
    }

    public Insert setValues(String... values) {
        for (String v : values) {
            valuesQuestion.append("?,");
            this.values.add(v);
        }
        return this;
    }


    public Insert setTable(String table) {
        this.table = table;
        return this;
    }

    public String write() throws InvalidSqlGenerationException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ")
                .append(table)
                .append(" ( ")
                .append(columns)
                .append(" ) ")
                .append(" VALUES (")
                .append(this.valuesQuestion.substring(0, valuesQuestion.length() - 2))
                .append(" )");
        return sql.toString();
    }

}
