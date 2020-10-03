package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.TipoItem;
import java.util.*;
import org.mybatis.guice.transactional.Transactional;

public interface TipoItemDAO {

    public TipoItem load(int id) throws PersistenceException;

    @Transactional
    public void save(String des) throws PersistenceException;
    public List<TipoItem> loadAll() throws PersistenceException;
}