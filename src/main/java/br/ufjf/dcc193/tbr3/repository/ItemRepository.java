package br.ufjf.dcc193.tbr3.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.tbr3.model.Item;

public interface ItemRepository extends JpaRepository <Item, Long> {

    
}