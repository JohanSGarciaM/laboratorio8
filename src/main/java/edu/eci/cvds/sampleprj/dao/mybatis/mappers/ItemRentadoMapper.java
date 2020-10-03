package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoMapper {

    public List<ItemRentado> consultarItemsCliente(@Param("id_cliente") long idcliente);

    public int consultarDiasAlquiler(@Param("id_item") int itemId);
}
