package br.school.model.dao.util;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoOperationsInterface<T extends Serializable> {

    T inserir(T value);

    T atualizar(T value);

    List<T> buscarTodos();

    T buscarId(Integer id);

    void excluir(T value);

}
