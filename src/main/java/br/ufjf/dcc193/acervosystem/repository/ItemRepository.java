package br.ufjf.dcc193.acervosystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.acervosystem.model.Item;

public interface ItemRepository extends JpaRepository <Item, Long> {

    public List<Item> findByIdNot(Long id);
    
}