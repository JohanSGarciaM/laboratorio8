package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.*;

public interface ItemRentadoDAO {

    public int consultarDiasAlquiler(int itemId);
    public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException;

}
