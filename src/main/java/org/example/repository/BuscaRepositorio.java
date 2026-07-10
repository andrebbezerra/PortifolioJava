package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface BuscaRepositorio <T>{
    Optional<T> buscarPorNome(String nome);
}
