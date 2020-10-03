package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Override
    public int consultarDiasAlquiler(int itemId) {
        return itemRentadoMapper.consultarDiasAlquiler(itemId);
    }

    @Override
    public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException {
        try {
            return itemRentadoMapper.consultarItemsCliente(idcliente);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar clientes", e);
        }
    }

}
