package com.rikazzo.back.service;

import com.rikazzo.back.entity.Categoria;
import com.rikazzo.back.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAll(){
        return this.categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> findById(Integer idCategoria){
        return this.categoriaRepository.findById(idCategoria);
    }

    @Transactional(rollbackFor = Exception.class)
    public Categoria agregarCategoria(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    @Transactional(rollbackFor = Exception.class)
    public Categoria actualizarCategoria(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarCategoria(Integer idCategoria){
        this.categoriaRepository.deleteById(idCategoria);
    }
}
